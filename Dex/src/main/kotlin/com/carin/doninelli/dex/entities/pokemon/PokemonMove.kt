package com.carin.doninelli.dex.entities.pokemon

import com.carin.doninelli.dex.internal.deserializers.PokemonMoveDeserializer
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.io.Serializable

@JsonDeserialize(using = PokemonMoveDeserializer::class)
sealed class PokemonMove : Serializable {
    abstract val name: String
}


data class LvlUpPokemonMove internal constructor(
        val level: Int,

        @JsonProperty("move")
        override val name: String
) : PokemonMove(), Serializable


data class TmPokemonMove internal constructor(
        val tm: String,

        @JsonProperty("move")
        override val name: String
) : PokemonMove(), Serializable


data class ParentPokemonMove internal constructor(
        val parent: String,

        @JsonProperty("move")
        override val name: String
) : PokemonMove(), Serializable


data class ItemOnParentPokemonMove internal constructor(
        val itemOnParent: String,

        @JsonProperty("move")
        override val name: String
) : PokemonMove(), Serializable