package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.dex.entities.type.Type;
import com.carin.doninelli.dex.entities.type.TypeInfo;
import com.carin.doninelli.midna.bot.messages.LogMessage;
import com.carin.doninelli.midna.bot.resource.PropertySource;
import com.carin.doninelli.midna.bot.resource.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

import java.util.Map;

final class TypeInfoEmbedMapper implements EmbedMapper<TypeInfo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeInfoEmbedMapper.class);

    private static final String TYPE_THUMBNAIL_FORMAT_PROPERTY = "type_format";

    private final ResourceService resourceService;

    TypeInfoEmbedMapper() {
        resourceService = ResourceService.INSTANCE;
    }

    @Override
    public EmbedObject map(TypeInfo object) {
        String thumbnailFormat = resourceService.getProperty(PropertySource.THUMBNAILS, TYPE_THUMBNAIL_FORMAT_PROPERTY);
        String thumbnail = String.format(thumbnailFormat, object.getNames().getEnglish().toLowerCase());
        LOGGER.debug(LogMessage.THUMBNAIL_FOR_TYPE_FOUND.getValue(), object.getNames().getEnglish(), thumbnail);

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .withTitle(object.getNames().getEnglish())
                .withColor(object.getColor())
                .withThumbnail(thumbnail);

        for (Map.Entry<Type, Double> entry : object.getEffectiveness().entrySet()) {
            Type type = entry.getKey();
            double effectiveness = entry.getValue();

            int effectivenessPercentage = (int) (100 * effectiveness);
            embedBuilder.appendField(type.name(), String.valueOf(effectivenessPercentage) + "%", true);
        }

        return embedBuilder.build();
    }
}
