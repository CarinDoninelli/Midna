package com.carin.doninelli.midna.bot.commands;

import sx.blah.discord.handle.obj.IMessage;

import java.util.List;

public interface Command {
    String getDescription();

    String getPrefix();

    String getName();

    List<String> getUsageWords();

    void execute(IMessage message, String commandContent);
}
