package com.carin.doninelli.dex.entities.move

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Move internal constructor(

        @JsonProperty("index_number")
        val index: Int,

        val pp: Int,

        val maxPp: Int,

        val power: Int,

        val accuracy: Int,

        val category: Category,

        val priority: Int,

        val target: Target

) : Serializable