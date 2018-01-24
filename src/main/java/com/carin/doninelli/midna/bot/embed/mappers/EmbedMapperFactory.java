package com.carin.doninelli.midna.bot.embed.mappers;

import com.carin.doninelli.dex.entities.ability.Ability;
import com.carin.doninelli.dex.entities.move.Move;
import com.carin.doninelli.dex.entities.pokemon.Pokemon;
import com.carin.doninelli.dex.entities.type.TypeInfo;
import com.carin.doninelli.midna.bot.reflect.TypeToken;
import com.carin.doninelli.midna.bot.resource.ResourceService;
import com.carin.doninelli.wolfram.entities.Pod;

import java.util.List;

public final class EmbedMapperFactory {

    private static final java.lang.reflect.Type POD_LIST_TYPE = new TypeToken<List<Pod>>() {}.getType();

    public <T> EmbedMapper<T> createMapper(Class<? extends T> clazz) {
        return createMapper(TypeToken.get(clazz).getType());
    }

    public <T> EmbedMapper<T> createMapper(java.lang.reflect.Type type) {
        EmbedMapper<?> mapper;
        if (type.equals(Pokemon.class)) {
            mapper = createPokemonEmbedMapper();
        } else if (type.equals(Ability.class)) {
            mapper = createAbilityEmbedMapper();
        } else if (type.equals(Move.class)) {
            mapper = createMoveEmbedMapper();
        } else if (type.equals(POD_LIST_TYPE)) {
            mapper = createWolframPodEmbedMapper();
        } else if (type.equals(TypeInfo.class)) {
            mapper = createTypeInfoEmbedMapper();
        } else {
            throw new IllegalArgumentException("No Mapper Defined '" + type.getTypeName() + "'");
        }

        //noinspection unchecked
        return (EmbedMapper<T>) mapper;
    }

    public EmbedMapper<TypeInfo> createTypeInfoEmbedMapper() {
        return new TypeInfoEmbedMapper();
    }

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
