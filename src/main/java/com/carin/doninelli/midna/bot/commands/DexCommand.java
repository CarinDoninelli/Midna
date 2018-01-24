package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.dex.Dex;
import com.carin.doninelli.dex.entities.ability.Ability;
import com.carin.doninelli.dex.entities.move.Move;
import com.carin.doninelli.dex.entities.pokemon.Pokemon;
import com.carin.doninelli.dex.entities.type.Type;
import com.carin.doninelli.dex.entities.type.TypeInfo;
import com.carin.doninelli.midna.bot.commands.services.ResponseService;
import com.carin.doninelli.midna.bot.embed.mappers.EmbedMapper;
import com.carin.doninelli.midna.bot.embed.mappers.EmbedMapperFactory;
import com.carin.doninelli.midna.bot.messages.LogMessage;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public final class DexCommand extends ReplyingCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(DexCommand.class);

    private static final String POKEMON_SUB_COMMAND = "pokemon";
    private static final String ABILITY_SUB_COMMAND = "ability";
    private static final String MOVE_SUB_COMMAND = "move";
    private static final String TYPE_SUB_COMMAND = "type";

    private final Dex dex;
    private final EmbedMapperFactory embedMapperFactory;

    public DexCommand(Dex dex) {
        this(dex, new ResponseService(true));
    }

    public DexCommand(Dex dex, ResponseService responseService) {
        super(responseService);
        this.dex = dex;
        embedMapperFactory = new EmbedMapperFactory();
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
            String[] tokens = commandContent.split("\\s+", 2);
            String subCommand = tokens[0];
            String subCommandContent = tokens.length > 1 ? tokens[1] : null;

            BiConsumer<MessageBuilder, String> searchAction = getSubCommandAction(subCommand);
            if (searchAction == null) {
                LOGGER.info(LogMessage.INVALID_DEX_SUB_COMMAND.getValue(), subCommand);
                response.withContent("`I did not understand that!.`");
            } else {
                searchAction.accept(response, subCommandContent);
            }
        }

        return response;
    }

    @Nullable
    private BiConsumer<MessageBuilder, String> getSubCommandAction(String subCommand) {
        BiConsumer<MessageBuilder, String> action;
        if (subCommand.equalsIgnoreCase(POKEMON_SUB_COMMAND)) {
            action = this::searchPokemon;
        } else if (subCommand.equalsIgnoreCase(ABILITY_SUB_COMMAND)) {
            action = this::searchAbility;
        } else if (subCommand.equalsIgnoreCase(MOVE_SUB_COMMAND)) {
            action = this::searchMove;
        } else if (subCommand.equalsIgnoreCase(TYPE_SUB_COMMAND)) {
            action = this::searchType;
        } else {
            action = null;
        }

        return action;
    }

    private void searchType(MessageBuilder response, String subCommandContent) {
        if (subCommandContent == null || subCommandContent.isEmpty()) {
            response.withContent("`Please specify a type.`");
        } else {
            Optional<Type> type = Arrays.stream(Type.values())
                    .filter(it -> it.name().equalsIgnoreCase(subCommandContent))
                    .findFirst();

            if (type.isPresent()) {
                TypeInfo typeInfo = dex.searchTypeInfo(type.get());
                LOGGER.info(LogMessage.TYPE_INFO_FOUND.getValue(), type, typeInfo);
                EmbedMapper<TypeInfo> typeInfoEmbedMapper = embedMapperFactory.createMapper(typeInfo.getClass());

                EmbedObject embed = typeInfoEmbedMapper.map(typeInfo);
                response.withEmbed(embed);
            } else {
                response.withContent("`Type not found.`");
            }
        }
    }

    private void searchMove(MessageBuilder response, String subCommandContent) {
        if (subCommandContent == null || subCommandContent.isEmpty()) {
            response.withContent("`Please specify a move name.`");
        } else {
            Move move = dex.searchMove(subCommandContent);
            if (move == null) {
                response.withContent("`Move not found.`");
            } else {
                LOGGER.info(LogMessage.MOVE_WITH_QUERY_FOUND.getValue(), subCommandContent, move);
                EmbedMapper<Move> moveEmbedMapper = embedMapperFactory.createMapper(move.getClass());

                EmbedObject embed = moveEmbedMapper.map(move);
                response.withEmbed(embed);
            }
        }
    }

    private void searchAbility(MessageBuilder response, @Nullable String subCommandContent) {
        if (subCommandContent == null || subCommandContent.isEmpty()) {
            response.withContent("`Please specify an ability name.`");
        } else {
            Ability ability = dex.searchAbility(subCommandContent);
            if (ability == null) {
                response.withContent("`Ability not found.`");
            } else {
                LOGGER.info(LogMessage.ABILITY_WITH_QUERY_FOUND.getValue(), subCommandContent);
                EmbedMapper<Ability> abilityEmbedMapper = embedMapperFactory.createMapper(ability.getClass());

                EmbedObject embedObject = abilityEmbedMapper.map(ability);
                response.withEmbed(embedObject);
            }
        }
    }

    private void searchPokemon(MessageBuilder response, @Nullable String subCommandContent) {
        if (subCommandContent == null || subCommandContent.isEmpty()) {
            response.withContent("`Please specify a pokemon name.`");
        } else {
            Pokemon pokemon = dex.searchPokemon(subCommandContent);
            if (pokemon == null) {
                response.withContent("`Pokemon not found`");
            } else {
                LOGGER.info(LogMessage.POKEMON_WITH_QUERY_FOUND.getValue(), subCommandContent, pokemon);
                EmbedMapper<Pokemon> pokedexEntryEmbedMapper = embedMapperFactory.createMapper(pokemon.getClass());

                EmbedObject embed = pokedexEntryEmbedMapper.map(pokemon);
                response.withEmbed(embed);
            }
        }
    }
}
