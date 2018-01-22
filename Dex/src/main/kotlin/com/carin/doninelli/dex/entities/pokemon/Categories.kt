package com.carin.doninelli.dex.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Categories internal constructor(

        @JsonProperty("en")
        val english: String,

        @JsonProperty("de")
        val german: String

) : Serializable