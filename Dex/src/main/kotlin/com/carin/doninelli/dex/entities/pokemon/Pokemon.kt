package com.carin.doninelli.dex.entities.pokemon

import com.carin.doninelli.dex.deserializers.ColorDeserializer
import com.carin.doninelli.dex.deserializers.PokemonMeasurementDeserializer
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.awt.Color
import java.io.Serializable

data class Pokemon internal constructor(

        val names: Names,

        val nationalId: Int,

        val types: List<String>,

        val abilities: List<Ability>,

        val genderRatios: GenderRatios,

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

        @JsonDeserialize(using = ColorDeserializer::class)
        val color: Color,

        val baseFriendship: Int,

        val evYield: Stats,

        val baseStats: Stats,

        @JsonProperty("learnset")
        val learnSet: List<Move>,

        val categories: Categories,

        @JsonProperty("evolution_from")
        val evolvesFrom: String?,

        val evolutions: List<Map<String, Any>>

) : Serializable