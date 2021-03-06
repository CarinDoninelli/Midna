package com.carin.doninelli.dex.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class PokemonAbility internal constructor(

        val name: String,

        @JsonProperty(defaultValue = "false")
        val hidden: Boolean

) : Serializable