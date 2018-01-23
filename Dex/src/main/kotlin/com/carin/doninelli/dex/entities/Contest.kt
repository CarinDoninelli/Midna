package com.carin.doninelli.dex.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Contest internal constructor(

        @JsonProperty("contest")
        val name: String,

        val appeal: Int,

        val jam: Int,

        val condition: String

) : Serializable