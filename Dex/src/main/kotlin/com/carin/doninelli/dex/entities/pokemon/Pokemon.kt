package com.carin.doninelli.dex.entities.pokemon

import com.carin.doninelli.dex.entities.Generation
import com.carin.doninelli.dex.entities.Names
import com.carin.doninelli.dex.internal.deserializers.PokemonMeasurementDeserializer
import com.carin.doninelli.dex.internal.deserializers.PokemonTypesDeserializer
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.io.Serializable

data class Pokemon internal constructor(

        val names: Names,

        val nationalId: Int,

        @JsonDeserialize(using = PokemonTypesDeserializer::class)
        val types: PokemonTypes,

        val abilities: List<PokemonAbility>,

        val genderRatios: PokemonGenderRatios?,

        val catchRate: Int,

        val eggGroups: List<String>,

        @JsonProperty("height_eu")
        @JsonDeserialize(using = PokemonMeasurementDeserializer::class)
        val height: PokemonMeasurement,

        @JsonProperty("weight_eu")
        @JsonDeserialize(using = PokemonMeasurementDeserializer::class)
        val weight: PokemonMeasurement,

        @JsonProperty("base_exp_yield")
        val baseExperienceYield: Int,

        val levelingRate: String,

        val color: PokemonColor,

        val baseFriendship: Int,

        val evYield: PokemonStats,

        val baseStats: PokemonStats,

        @JsonProperty("learnset")
        val learnSet: List<PokemonMove>,

        val categories: PokemonCategories,

        @JsonProperty("evolution_from")
        val evolvesFrom: String?,

        val evolutions: List<Map<String, Any>>,

        val pokedexEntries: Map<Generation, PokemonPokedexEntry>

) : Serializable {

    val sprite: String get() {
        val spriteName = names.english.toLowerCase()
                .replace("\\.?\\s+".toRegex(), "_")
        return "http://www.pkparaiso.com/imagenes/xy/sprites/animados/$spriteName.gif"
    }
}