package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.dex.Dex;
import com.carin.doninelli.dex.entities.pokemon.Pokemon;
import com.carin.doninelli.midna.bot.commands.services.ResponseService;
import com.carin.doninelli.midna.bot.embed.mappers.PokedexEntryEmbedMapper;
import com.carin.doninelli.midna.bot.messages.LogMessage;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

import java.util.Collections;
import java.util.List;

public final class DexCommand extends ReplyingCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(DexCommand.class);

    private final Dex dex;
    private final PokedexEntryEmbedMapper pokedexEntryEmbedMapper;

    public DexCommand(Dex dex) {
        this(dex, new ResponseService(true));
    }

    public DexCommand(Dex dex, ResponseService responseService) {
        super(responseService);
        this.dex = dex;
        this.pokedexEntryEmbedMapper = new PokedexEntryEmbedMapper();
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
    MessageBuilder buildResponse(IMessage message, @Nullable String commandContent) {
        MessageBuilder response = new MessageBuilder(message.getClient())
                .withChannel(message.getChannel());

        if (commandContent == null) {
            response.withContent("`Missing query.`");
        } else {
            Pokemon pokemon = dex.searchPokemon(commandContent);
            if (pokemon == null) {
                response.withContent("`No pokemon with name " + commandContent + " found.`");
            } else {
                LOGGER.info(LogMessage.POKEMON_WITH_QUERY_FOUND.getValue(), commandContent, pokemon);
                EmbedObject embed = pokedexEntryEmbedMapper.map(pokemon);
                response.withEmbed(embed);
            }
        }

        return response;
    }
}
