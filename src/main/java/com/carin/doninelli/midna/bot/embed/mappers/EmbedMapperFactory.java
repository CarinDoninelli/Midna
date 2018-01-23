package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.dex.entities.ability.Ability;
import com.carin.doninelli.dex.entities.move.Move;
import com.carin.doninelli.dex.entities.pokemon.Pokemon;
import com.carin.doninelli.midna.bot.resource.ResourceService;
import com.carin.doninelli.wolfram.entities.Pod;

import java.util.List;

public final class EmbedMapperFactory {
    public EmbedMapper<Pokemon> createPokemonEmbedMapper() {
        return new PokedexEntryEmbedMapper();
    }

    public EmbedMapper<List<Pod>> createWolframPodEmbedMapper() {
        return new WolframPodsEmbedMapper();
    }

    public EmbedMapper<Ability> createAbilityEmbedMapper() {
        return new AbilityEmbedMapper(ResourceService.INSTANCE);
    }

    public EmbedMapper<Move> createMoveEmbedMapper() {
        return new MoveEmbedMapper(ResourceService.INSTANCE);
    }
}
