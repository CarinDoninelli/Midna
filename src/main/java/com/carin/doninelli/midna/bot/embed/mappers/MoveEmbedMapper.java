package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.dex.entities.move.Move;
import com.carin.doninelli.midna.bot.resource.PropertySource;
import com.carin.doninelli.midna.bot.resource.ResourceService;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.Color;

final class MoveEmbedMapper implements EmbedMapper<Move> {

    private static final String MOVE_THUMBNAIL_PROPERTY = "move";

    private final ResourceService resourceService;

    MoveEmbedMapper(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public EmbedObject map(Move object) {
        return new EmbedBuilder()
                .withTitle(object.getNames().getEnglish() + " [ " + object.getCategory().name() +" ]")
                .withColor(Color.red)
                .withThumbnail(resourceService.getProperty(PropertySource.THUMBNAILS, MOVE_THUMBNAIL_PROPERTY))
                .appendField("PP", String.valueOf(object.getPp()), true)
                .appendField("Max PP", String.valueOf(object.getMaxPp()), true)
                .appendField("Accuracy", String.valueOf(object.getAccuracy()), true)
                .appendField("Power", String.valueOf(object.getPower()), true)
                .appendField("Target", object.getTarget().name(), true)
                .appendField("Priority", String.valueOf(object.getPriority()), true)
                .build();
    }
}
