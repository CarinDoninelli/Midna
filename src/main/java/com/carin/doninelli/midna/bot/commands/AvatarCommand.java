package com.carin.doninelli.midna.bot.commands;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class AvatarCommand implements Command {

    @Override
    public String getDescription() {
        return "Displays a user's avatar.";
    }

    @Override
    public String getPrefix() {
        return "!";
    }

    @Override
    public String getName() {
        return "avatar";
    }

    @Override
    public List<String> getUsageWords() {
        return Collections.singletonList("avatar");
    }

    @Override
    public void execute(IMessage message, String commandContent) {
        Optional<IUser> user = message.getMentions().stream()
                                      .findFirst();

        if (user.isPresent()) {
            String avatar = user.get().getAvatarURL().concat("?size=1024");
            EmbedObject embed = new EmbedBuilder()
                    .withImage(avatar)
                    .withFooterIcon(avatar)
                    .withFooterText(user.get().getDisplayName(message.getGuild()))
                    .build();
            message.reply("", embed);
        } else {
            message.reply("`User not found`");
        }
    }
}
