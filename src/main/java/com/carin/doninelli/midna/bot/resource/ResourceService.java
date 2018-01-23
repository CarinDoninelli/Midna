package com.carin.doninelli.midna.bot.resource;

import com.carin.doninelli.midna.bot.exceptions.ResourceNotFound;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum ResourceService {
    INSTANCE;

    private final ConcurrentMap<PropertySource, Properties> propertyMap;

    ResourceService() {
        this.propertyMap = new ConcurrentHashMap<>();
    }

    public String getProperty(PropertySource source, String propertyName) {
        Properties properties = propertyMap.computeIfAbsent(source, this::loadProperties);
        String property = properties.getProperty(propertyName);

        if (property == null) {
            throw new ResourceNotFound(source.getFileName() + "-" + propertyName);
        } else {
            return property;
        }
    }

    private Properties loadProperties(PropertySource source) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(source.getFileName())) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
