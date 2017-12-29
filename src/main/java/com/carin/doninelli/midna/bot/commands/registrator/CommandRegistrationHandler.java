package com.carin.doninelli.midna.bot.commands.registrator;

import com.carin.doninelli.midna.bot.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public final class CommandRegistrationHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CommandRegistrationHandler.class);

    private final IDiscordClient discordClient;

    public CommandRegistrationHandler(IDiscordClient discordClient) {
        this.discordClient = discordClient;
    }

    public void register(Command command) {
        discordClient.getDispatcher().registerListener((IListener<MessageReceivedEvent>) event -> {
            if (event.getMessage().getChannel().isPrivate()) {
                return;
            }

            String content = event.getMessage().getContent();
            List<String> tokens = Arrays.asList(content.split("\\s+", 2));

            if (tokens.isEmpty()) {
                return;
            }

            String commandToken = tokens.get(0);
            if (command.getUsageWords().stream().anyMatch(it -> command.getPrefix().concat(it).equals(commandToken))) {
                String trimmedContent = tokens.size() == 2 ? tokens.get(1) : null;

                LOG.info("{} command executed.", command.getName());
                command.execute(event.getMessage(), trimmedContent);
            }
        });
    }
}
