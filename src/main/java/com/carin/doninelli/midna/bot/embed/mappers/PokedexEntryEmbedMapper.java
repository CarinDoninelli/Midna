package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.dex.entities.pokemon.Pokemon;
import com.carin.doninelli.midna.bot.messages.LogMessage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

import java.util.stream.Collectors;

public final class PokedexEntryEmbedMapper implements EmbedMapper<Pokemon> {
    private static final Logger LOG = LoggerFactory.getLogger(PokedexEntryEmbedMapper.class);

    private static final String AUTHOR_NAME = "Dex";
    private static final String AUTHOR_ICON_URL = "https://vignette.wikia.nocookie.net/clubpenguin/images/4/4c/Pokeball.png/revision/latest?cb=20130901024704";

    @Override
    public EmbedObject map(@NotNull Pokemon pokemon) {
        String typesField = pokemon.getTypes().getFirst();
        if (pokemon.getTypes().getSecond() != null) {
            typesField += "\n";
            typesField += pokemon.getTypes().getSecond();
        }

        String abilitiesField = pokemon.getAbilities().stream()
                .map(ability -> ability.getName() + (ability.getHidden() ? "\t(hidden)" : ""))
                .collect(Collectors.joining("\n"));

        LOG.debug(LogMessage.POKEMON_SPRITE.getValue(), pokemon.getNames().getEnglish(), pokemon.getSprite());

        return new EmbedBuilder()
                .withTitle(pokemon.getNames().getEnglish() + " #" + pokemon.getNationalId())
                .withAuthorName(AUTHOR_NAME)
                .withAuthorIcon(AUTHOR_ICON_URL)
                .withThumbnail(pokemon.getSprite())
                .withColor(pokemon.getColor().getAwt())
                .appendField("Types", typesField, true)
                .appendField("Abilities", abilitiesField, true)
                .withFooterText(pokemon.getNames().getEnglish())
                .withFooterIcon(pokemon.getSprite())
                .build();
    }
}
