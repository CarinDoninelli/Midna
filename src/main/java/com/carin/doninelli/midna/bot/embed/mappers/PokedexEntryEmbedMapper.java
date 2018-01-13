package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.dex.entities.Pokemon;
import com.carin.doninelli.dex.entities.Type;
import org.jetbrains.annotations.NotNull;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.stream.Collectors;

public final class PokedexEntryEmbedMapper implements EmbedMapper<Pokemon> {

    @Override
    public EmbedObject map(@NotNull Pokemon pokemon) {
        String typesField = pokemon.getTypes().stream()
                .map(Type::getName)
                .collect(Collectors.joining(", "));

        return new EmbedBuilder()
                .withTitle(pokemon.getName() + " #" + pokemon.getId())
                .withColor(Color.red)
                .appendField("Types", typesField, false)
                .build();
    }
}
