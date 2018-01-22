package com.carin.doninelli.midna.bot;

import com.carin.doninelli.core.loader.ResourceLoader;
import com.carin.doninelli.dex.factory.DexFactory;
import com.carin.doninelli.midna.bot.commands.AvatarCommand;
import com.carin.doninelli.midna.bot.commands.Command;
import com.carin.doninelli.midna.bot.commands.DexCommand;
import com.carin.doninelli.midna.bot.commands.HelpCommand;
import com.carin.doninelli.midna.bot.commands.WolframCommand;
import com.carin.doninelli.midna.bot.commands.registrator.CommandRegistrationHandler;
import com.carin.doninelli.wolfram.factory.WolframFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import java.util.Arrays;
import java.util.List;

public final class Midna {

    private static final String CREDENTIALS_PROPERTIES = "credentials.properties";

    public static void main(String[] args) {
        ResourceLoader loader = new ResourceLoader();
        String token = loader.getProperty(CREDENTIALS_PROPERTIES, "token");
        String wolframAppId = loader.getProperty(CREDENTIALS_PROPERTIES, "wolfram");

        IDiscordClient discordClient = new ClientBuilder().withToken(token).login();
        CommandRegistrationHandler commandRegistrationHandler = new CommandRegistrationHandler(discordClient);

        DexFactory dexFactory = new DexFactory();
        WolframFactory wolframFactory = new WolframFactory();

        ResponseService responseService = new ResponseService(true);

        List<Command> commands = Arrays.asList(
                new AvatarCommand(),
                new DexCommand(dexFactory.createDex(), responseService),
                new WolframCommand(wolframFactory.createWolframClient(wolframAppId), responseService)
        );

        commandRegistrationHandler.registerAll(commands);
        commandRegistrationHandler.register(new HelpCommand(commands));
    }
}
