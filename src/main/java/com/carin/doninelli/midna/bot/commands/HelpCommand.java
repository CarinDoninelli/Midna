package com.carin.doninelli.midna.bot.commands;

import org.jetbrains.annotations.Nullable;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HelpCommand implements Command {

    private final List<Command> commands;

    public HelpCommand(List<Command> commands) {
        this.commands = new ArrayList<>(commands);
        this.commands.add(0, this);
    }

    @Override
    public String getDescription() {
        return "Displays the list of available commands.";
    }

    @Override
    public String getPrefix() {
        return "!";
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public List<String> getUsageWords() {
        return Collections.singletonList("help");
    }

    @Override
    public void execute(IMessage message, @Nullable String commandContent) {
        message.reply("", buildHelpMessage());
    }

    private EmbedObject buildHelpMessage() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        for (Command command : commands) {
            String commandTitle = command.getName() + " | " + command.getPrefix() + command.getUsageWords();
            embedBuilder.appendField(commandTitle, command.getDescription(), false);
        }

        return embedBuilder.build();
    }
}
