package com.carin.doninelli.midna.bot.commands.registrator;

import com.carin.doninelli.midna.bot.commands.Command;
import com.carin.doninelli.midna.bot.messages.LogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;

import java.util.Collection;

public final class CommandRegistrationHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CommandRegistrationHandler.class);

    private final IDiscordClient discordClient;

    public CommandRegistrationHandler(IDiscordClient discordClient) {
        this.discordClient = discordClient;
    }

    public void register(Command command) {
        CommandReceivedListener listener = new CommandReceivedListener(command);
        discordClient.getDispatcher()
                .registerListener(listener);
        LOG.info(LogMessage.COMMAND_REGISTERED.getValue(), command.getName());
    }

    public void registerAll(Collection<Command> commands) {
        commands.forEach(this::register);
    }
}
