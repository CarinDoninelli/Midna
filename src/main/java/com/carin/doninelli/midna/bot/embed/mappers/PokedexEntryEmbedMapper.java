package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.dex.entities.Type;
import com.carin.doninelli.dex.entities.pokemon.Pokemon;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

import java.util.stream.Collectors;

public final class PokedexEntryEmbedMapper implements EmbedMapper<Pokemon> {
    private static final Logger LOG = LoggerFactory.getLogger(PokedexEntryEmbedMapper.class);

    private static final String SPRITE_URL_FORMAT = "http://www.pkparaiso.com/imagenes/xy/sprites/animados/%s.gif";

    @Override
    public EmbedObject map(@NotNull Pokemon pokemon) {
        String typesField = pokemon.getTypes().stream()
                .map(Type::getName)
                .collect(Collectors.joining("\n"));

        String abilitiesField = pokemon.getAbilities().stream()
                .map(ability -> ability.getName() + (ability.isHidden() ? "\t(hidden)" : ""))
                .collect(Collectors.joining("\n"));

        String pokemonName = pokemon.getName().replace(" ", "_").toLowerCase();
        String sprite = String.format(SPRITE_URL_FORMAT, pokemonName);
        LOG.info("id: {}, sprite: {}", pokemon.getId(), sprite);

        return new EmbedBuilder()
                .withTitle(pokemon.getName() + " #" + pokemon.getId())
                .withThumbnail(sprite)
                .withColor(pokemon.getColor().getAwt())
                .appendField("Types", typesField, true)
                .appendField("Abilities", abilitiesField, true)
                .build();
    }
}
