package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.midna.bot.commands.services.ResponseService;
import com.carin.doninelli.midna.bot.embed.mappers.EmbedMapper;
import com.carin.doninelli.midna.bot.embed.mappers.EmbedMapperFactory;
import com.carin.doninelli.wolfram.Wolfram;
import com.carin.doninelli.wolfram.entities.Pod;
import com.carin.doninelli.wolfram.entities.WolframResult;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

import java.util.Arrays;
import java.util.List;

public final class WolframCommand extends ReplyingCommand {
    private static final Logger LOG = LoggerFactory.getLogger(WolframCommand.class);

    private final Wolfram wolfram;

    public WolframCommand(Wolfram wolfram) {
        this(wolfram, new ResponseService(true));
    }

    public WolframCommand(Wolfram wolfram, ResponseService responseService) {
        super(responseService);
        this.wolfram = wolfram;
    }


    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getPrefix() {
        return "!";
    }

    @Override
    public String getName() {
        return "Wolfram Alpha";
    }

    @Override
    public List<String> getUsageWords() {
        return Arrays.asList("wolfram", "eval");
    }

    @Override
    MessageBuilder buildResponse(IMessage message, @Nullable String commandContent) {
        MessageBuilder response = new MessageBuilder(message.getClient())
                .withChannel(message.getChannel());

        if (commandContent == null) {
            response.withContent("`Please provide a query.`");
        } else {
            WolframResult result = wolfram.evaluateQuery(commandContent);
            LOG.info("Wolfram query '{}' with result: '{}'", commandContent, result);

            if (result instanceof WolframResult.Success) {
                WolframResult.Success successResult = (WolframResult.Success) result;
                EmbedMapperFactory embedMapperFactory = new EmbedMapperFactory();
                EmbedMapper<List<Pod>> wolframPodsEmbedMapper = embedMapperFactory.createWolframPodEmbedMapper();

                EmbedObject embed = wolframPodsEmbedMapper.map(successResult.getPods());
                response.withEmbed(embed);
            } else {
                WolframResult.Failure failedResult = (WolframResult.Failure) result;
                response.withContent("`" + failedResult.getMessage() + "`");
            }
        }

        return response;
    }
}
