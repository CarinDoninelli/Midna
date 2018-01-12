package com.carin.doninelli.midna.bot.commands.registrator;

import com.carin.doninelli.midna.bot.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class CommandRegistrationHandler {

    private final IDiscordClient discordClient;

    public CommandRegistrationHandler(IDiscordClient discordClient) {
        this.discordClient = discordClient;
    }

    public void register(Command command) {
        CommandReceivedListener listener = new CommandReceivedListener(command);
        discordClient.getDispatcher()
                .registerListener(listener);
    }
}
