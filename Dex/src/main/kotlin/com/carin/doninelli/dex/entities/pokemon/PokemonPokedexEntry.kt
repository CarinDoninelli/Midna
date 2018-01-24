package com.carin.doninelli.dex.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class PokemonPokedexEntry internal constructor(

        @JsonProperty("en")
        val english: String,

        @JsonProperty("de")
        val german: String

) : Serializable