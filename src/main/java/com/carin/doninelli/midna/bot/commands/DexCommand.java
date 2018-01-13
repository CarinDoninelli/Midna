package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.dex.Dex;
import com.carin.doninelli.dex.entities.Pokemon;
import com.carin.doninelli.midna.bot.ResponseService;
import com.carin.doninelli.midna.bot.embed.mappers.PokedexEntryEmbedMapper;
import com.carin.doninelli.midna.bot.util.NumberUtil;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

import java.util.Collections;
import java.util.List;

public final class DexCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(DexCommand.class);

    private final Dex dex;
    private final ResponseService responseService;
    private final PokedexEntryEmbedMapper pokedexEntryEmbedMapper;

    public DexCommand(Dex dex) {
        this.dex = dex;
        responseService = new ResponseService(true);
        pokedexEntryEmbedMapper = new PokedexEntryEmbedMapper();
    }

    @Override
    public String getDescription() {
        return "Displays dex info for a specified pokemon.";
    }

    @Override
    public String getPrefix() {
        return "!";
    }

    @Override
    public String getName() {
        return "Dex";
    }

    @Override
    public List<String> getUsageWords() {
        return Collections.singletonList("dex");
    }

    @Override
    public void execute(IMessage message, @Nullable String commandContent) {
        MessageBuilder response = new MessageBuilder(message.getClient())
                .withChannel(message.getChannel());

        if (commandContent == null) {
            response.appendContent("`Please specify a pokemon name or id.`");
            responseService.bufferResponseRequest(response);
            return;
        }

        Pokemon pokemon;
        if (NumberUtil.isInt(commandContent)) {
            int pokemonId = Integer.parseInt(commandContent);
            pokemon = dex.searchPokemon(pokemonId);
        } else {
            pokemon = dex.searchPokemon(commandContent);
        }

        if (pokemon == null) {
            response.appendContent("`No info found on ".concat(commandContent).concat("`"));
        } else {
            LOGGER.info("Pokemon with query {} found. {}", commandContent, pokemon);
            EmbedObject embed = pokedexEntryEmbedMapper.map(pokemon);
            response.withEmbed(embed);
        }

        responseService.bufferResponseRequest(response);
    }
}
