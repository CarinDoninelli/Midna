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
        val types: Types,

        val abilities: List<Ability>,

        val genderRatios: GenderRatios?,

        val catchRate: Int,

        val eggGroups: List<String>,

        @JsonProperty("height_eu")
        @JsonDeserialize(using = PokemonMeasurementDeserializer::class)
        val height: Measurement,

        @JsonProperty("weight_eu")
        @JsonDeserialize(using = PokemonMeasurementDeserializer::class)
        val weight: Measurement,

        @JsonProperty("base_exp_yield")
        val baseExperienceYield: Int,

        val levelingRate: String,

        val color: Color,

        val baseFriendship: Int,

        val evYield: Stats,

        val baseStats: Stats,

        @JsonProperty("learnset")
        val learnSet: List<Move>,

        val categories: Categories,

        @JsonProperty("evolution_from")
        val evolvesFrom: String?,

        val evolutions: List<Map<String, Any>>,

        val pokedexEntries: Map<Generation, PokedexEntry>

) : Serializable {

    val sprite: String get() {
        val spriteName = names.english.toLowerCase()
                .replace("\\.?\\s+".toRegex(), "_")
        return "http://www.pkparaiso.com/imagenes/xy/sprites/animados/$spriteName.gif"
    }
}