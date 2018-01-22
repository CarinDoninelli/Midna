package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.midna.bot.commands.services.ResponseService;
import org.jetbrains.annotations.Nullable;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class AvatarCommand extends ReplyingCommand {

    public AvatarCommand() {
        this(new ResponseService(true));
    }

    public AvatarCommand(ResponseService responseService) {
        super(responseService);
    }

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
    MessageBuilder buildResponse(IMessage message, @Nullable String commandContent) {
        Optional<IUser> user = message.getMentions().stream()
                .findFirst();

        MessageBuilder response = new MessageBuilder(message.getClient())
                .withChannel(message.getChannel());
        if (user.isPresent()) {
            String avatar = user.get().getAvatarURL().concat("?size=1024");
            EmbedObject embed = new EmbedBuilder()
                    .withImage(avatar)
                    .withFooterIcon(avatar)
                    .withFooterText(user.get().getDisplayName(message.getGuild()))
                    .build();
            response.withEmbed(embed);
        } else {
            response.withContent("`User not found`");
        }

        return response;
    }
}
