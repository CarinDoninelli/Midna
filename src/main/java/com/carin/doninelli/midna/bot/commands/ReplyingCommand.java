package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.midna.bot.commands.services.ResponseService;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

public abstract class ReplyingCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyingCommand.class);

    private final ResponseService responseService;

    ReplyingCommand(ResponseService responseService) {
        this.responseService = responseService;
    }

    @Override
    public final void execute(IMessage message, @Nullable String commandContent) {
        try {
            MessageBuilder response = buildResponse(message, commandContent);
            responseService.bufferResponseRequest(response);
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
        }
    }

    abstract MessageBuilder buildResponse(IMessage message, @Nullable String commandContent);
}
