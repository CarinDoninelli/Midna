package com.carin.doninelli.midna.bot.resource;

import com.carin.doninelli.midna.bot.exceptions.ResourceNotFoundException;
import com.carin.doninelli.midna.bot.messages.LogMessage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum ResourceService {
    INSTANCE;

    private final ConcurrentMap<PropertySource, Properties> propertyMap;
    private final Logger logger;

    ResourceService() {
        this.propertyMap = new ConcurrentHashMap<>();
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @NotNull
    public String getProperty(PropertySource source, String propertyName) {
        Properties properties = propertyMap.computeIfAbsent(source, this::loadProperties);
        String property = properties.getProperty(propertyName);

        if (property == null) {
            throw new ResourceNotFoundException(source.getFileName() + "[" + propertyName + "]");
        } else {
            return property;
        }
    }

    @NotNull
    private Properties loadProperties(PropertySource source) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(source.getFileName())) {
            properties.load(inputStream);
            logger.debug(LogMessage.PROPERTIES_LOADED.getValue(), source.getFileName(), properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
