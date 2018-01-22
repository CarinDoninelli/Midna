package com.carin.doninelli.midna.bot.embed.mappers;

import sx.blah.discord.api.internal.json.objects.EmbedObject;

@FunctionalInterface
public interface EmbedMapper <T> {
    EmbedObject map(T object);
}
