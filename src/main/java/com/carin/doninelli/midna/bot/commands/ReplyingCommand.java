package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.midna.bot.commands.services.ResponseService;
import org.jetbrains.annotations.Nullable;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

abstract class ReplyingCommand implements Command {

    private final ResponseService responseService;

    ReplyingCommand(ResponseService responseService) {
        this.responseService = responseService;
    }

    @Override
    public final void execute(IMessage message, @Nullable String commandContent) {
        MessageBuilder response = buildResponse(message, commandContent);
        responseService.bufferResponseRequest(response);
    }

    abstract MessageBuilder buildResponse(IMessage message, @Nullable String commandContent);
}
