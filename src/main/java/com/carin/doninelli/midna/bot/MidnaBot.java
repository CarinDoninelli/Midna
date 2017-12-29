package com.carin.doninelli.midna.bot;

import com.carin.doninelli.midna.bot.commands.AvatarCommand;
import com.carin.doninelli.midna.bot.commands.Command;
import com.carin.doninelli.midna.bot.commands.HelpCommand;
import com.carin.doninelli.midna.bot.commands.registrator.CommandRegistrationHandler;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public final class MidnaBot {
    public static void main(String[] args) {
        String token = readToken();

        IDiscordClient discordClient = new ClientBuilder().withToken(token).login();
        CommandRegistrationHandler commandRegistrationHandler = new CommandRegistrationHandler(discordClient);

        List<Command> commands = Collections.singletonList(new AvatarCommand());

        commands.forEach(commandRegistrationHandler::register);
        commandRegistrationHandler.register(new HelpCommand(commands));
    }

    private static String readToken() {
        try (InputStream input = MidnaBot.class.getClassLoader().getResourceAsStream("credentials.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            return properties.getProperty("token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
