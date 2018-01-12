package com.carin.doninelli.midna.bot.commands;

import org.jetbrains.annotations.Nullable;
import sx.blah.discord.handle.obj.IMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public interface Command {
    String getDescription();

    String getPrefix();

    String getName();

    List<String> getUsageWords();

    default List<String> getPrefixedUsageWords() {
        List<String> usageWords = getUsageWords();
        List<String> words = new ArrayList<>(usageWords.size());
        for (String word : usageWords) {
            words.add(getPrefix() + word);
        }

        return words;
    }

    void execute(IMessage message, @Nullable String commandContent);
}
