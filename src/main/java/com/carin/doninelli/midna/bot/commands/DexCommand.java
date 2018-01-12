package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.dex.Dex;
import com.carin.doninelli.dex.entities.Pokemon;
import org.jetbrains.annotations.Nullable;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.util.Collections;
import java.util.List;

public final class DexCommand implements Command {

    private final Dex dex;

    public DexCommand(Dex dex) {
        this.dex = dex;
    }

    @Override
    public String getDescription() {
        return "Displays dex info for a specified pokemon.";
    }

    @Override
    public String getPrefix() {
        return "!";
    }

    @Override
    public String getName() {
        return "Dex";
    }

    @Override
    public List<String> getUsageWords() {
        return Collections.singletonList("dex");
    }

    @Override
    public void execute(IMessage message, @Nullable String commandContent) {
        if (commandContent == null) {
            message.reply("`Please specify a pokemon name or id.`");
            return;
        }

        Pokemon pokemon = dex.searchPokemon(commandContent).join();
        if (pokemon == null) {
            message.reply("`No info found on ".concat(commandContent).concat("`"));
        } else {
            EmbedObject embed = new EmbedBuilder()
                    .withTitle(getName())
                    .withDescription(pokemon.getName())
                    .build();
            message.reply("", embed);
        }
    }
}
