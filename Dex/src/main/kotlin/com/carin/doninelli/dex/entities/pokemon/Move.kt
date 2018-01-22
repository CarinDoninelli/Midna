package com.carin.doninelli.dex.entities.pokemon

import com.carin.doninelli.dex.deserializers.PokemonMoveDeserializer
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.io.Serializable

@JsonDeserialize(using = PokemonMoveDeserializer::class)
sealed class Move : Serializable {
    abstract val name: String
}


data class LvlUpMove internal constructor(
        val level: Int,

        @JsonProperty("move")
        override val name: String
) : Move(), Serializable


data class TmMove internal constructor(
        val tm: String,

        @JsonProperty("move")
        override val name: String
) : Move(), Serializable


data class ParentMove internal constructor(
        val parent: String,

        @JsonProperty("move")
        override val name: String
) : Move(), Serializable