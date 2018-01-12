package com.carin.doninelli.midna.bot.commands.registrator;

import com.carin.doninelli.midna.bot.commands.Command;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class CommandReceivedListener implements IListener<MessageReceivedEvent> {

    private final Command command;

    CommandReceivedListener(Command command) {
        this.command = command;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        if (event.getMessage().getChannel().isPrivate()) {
            return;
        }

        String content = event.getMessage().getContent();
        List<String> tokens = Arrays.asList(content.split("\\s+", 2));

        if (tokens.isEmpty()) {
            return;
        }

        String commandToken = tokens.get(0);
        if (isCommandCall(commandToken)) {
            String trimmedContent = tokens.size() == 2 ? tokens.get(1) : null;

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new CommandExecutionTask(command, trimmedContent, event));
            executorService.shutdown();
        }
    }

    private boolean isCommandCall(String commandToken) {
        return command.getPrefixedUsageWords()
                .stream()
                .anyMatch(it -> it.equals(commandToken));
    }
}
