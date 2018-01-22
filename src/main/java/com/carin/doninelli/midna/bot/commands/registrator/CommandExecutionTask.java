package com.carin.doninelli.midna.bot.commands.registrator;

import com.carin.doninelli.midna.bot.commands.Command;
import com.carin.doninelli.midna.bot.messages.LogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

final class CommandExecutionTask implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(CommandExecutionTask.class);

    private final Command command;
    private final String trimmedContent;
    private final MessageReceivedEvent event;

    CommandExecutionTask(Command command, String trimmedContent, MessageReceivedEvent event) {
        this.command = command;
        this.trimmedContent = trimmedContent;
        this.event = event;
    }

    @Override
    public void run() {
        LOG.info(LogMessage.COMMAND_CALLED.getValue(), command.getName());

        long startTime = System.currentTimeMillis();
        command.execute(event.getMessage(), trimmedContent);
        long endTime = System.currentTimeMillis();

        LOG.info(LogMessage.COMMAND_EXECUTED.getValue(), command.getName(), endTime - startTime);
    }
}
