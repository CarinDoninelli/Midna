package com.carin.doninelli.midna.bot;

import com.carin.doninelli.dex.DexFactory;
import com.carin.doninelli.midna.bot.commands.AvatarCommand;
import com.carin.doninelli.midna.bot.commands.Command;
import com.carin.doninelli.midna.bot.commands.DexCommand;
import com.carin.doninelli.midna.bot.commands.HelpCommand;
import com.carin.doninelli.midna.bot.commands.WolframCommand;
import com.carin.doninelli.midna.bot.commands.registrator.CommandRegistrationHandler;
import com.carin.doninelli.midna.bot.commands.services.ResponseService;
import com.carin.doninelli.midna.bot.resource.PropertySource;
import com.carin.doninelli.midna.bot.resource.ResourceService;
import com.carin.doninelli.wolfram.factory.WolframFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import java.util.Arrays;
import java.util.List;

public final class Midna {
    public static void main(String[] args) {
        ResourceService resourceService = ResourceService.INSTANCE;
        String token = resourceService.getProperty(PropertySource.CREDENTIALS, "token");
        String wolframAppId = resourceService.getProperty(PropertySource.CREDENTIALS, "wolfram");

        IDiscordClient discordClient = new ClientBuilder().withToken(token).login();
        CommandRegistrationHandler commandRegistrationHandler = new CommandRegistrationHandler(discordClient);

        DexFactory dexFactory = new DexFactory();
        WolframFactory wolframFactory = new WolframFactory();

        ResponseService responseService = new ResponseService(true);

        List<Command> commands = Arrays.asList(
                new AvatarCommand(responseService),
                new DexCommand(dexFactory.createDex(), responseService),
                new WolframCommand(wolframFactory.createWolframClient(wolframAppId), responseService)
        );

        commandRegistrationHandler.registerAll(commands);
        commandRegistrationHandler.register(new HelpCommand(commands));
    }
}
