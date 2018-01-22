package com.carin.doninelli.midna.bot.commands;

import com.carin.doninelli.dex.Dex;
import com.carin.doninelli.midna.bot.ResponseService;
import com.carin.doninelli.midna.bot.embed.mappers.PokedexEntryEmbedMapper;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Collections;
import java.util.List;

public final class DexCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(DexCommand.class);

    private final Dex dex;
    private final ResponseService responseService;
    private final PokedexEntryEmbedMapper pokedexEntryEmbedMapper;

    public DexCommand(Dex dex) {
        this(dex, new ResponseService(true));
    }

    public DexCommand(Dex dex, ResponseService responseService) {
        this.dex = dex;
        this.responseService = responseService;
        this.pokedexEntryEmbedMapper = new PokedexEntryEmbedMapper();
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

    }
}
