package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.dex.entities.ability.Ability;
import com.carin.doninelli.midna.bot.resource.PropertySource;
import com.carin.doninelli.midna.bot.resource.ResourceService;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.Color;

final class AbilityEmbedMapper implements EmbedMapper<Ability> {

    private static final String ABILITY_THUMBNAIL_PROPERTY = "ability";

    private final ResourceService resourceService;

    AbilityEmbedMapper(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public EmbedObject map(Ability object) {
        return new EmbedBuilder()
                .withColor(Color.red)
                .withTitle(object.getNames().getEnglish())
                .appendDescription(object.getDescriptions().getEnglish())
                .withThumbnail(resourceService.getProperty(PropertySource.THUMBNAILS, ABILITY_THUMBNAIL_PROPERTY))
                .build();
    }
}
