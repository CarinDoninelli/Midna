package com.carin.doninelli.midna.bot.commands;

import org.jetbrains.annotations.Nullable;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class EchoCommand implements Command {
    @Override
    public String getDescription() {
        return "Prints the specified message using embedded format";
    }

    @Override
    public String getPrefix() {
        return "!";
    }

    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public List<String> getUsageWords() {
        return Arrays.asList("echo", "repeat", "embed");
    }

    @Override
    public void execute(IMessage message, @Nullable String commandContent) {
        if (commandContent == null) {
            message.reply("`Please Provide a Message.`");
        } else {
            String authorName = Optional.ofNullable(message.getAuthor().getNicknameForGuild(message.getGuild()))
                    .orElse(message.getAuthor().getName());
            EmbedObject embed = new EmbedBuilder()
                    .withFooterIcon(message.getAuthor().getAvatarURL())
                    .withFooterText(authorName)
                    .withDescription(commandContent)
                    .build();
            message.reply("", embed);
        }
    }
}
