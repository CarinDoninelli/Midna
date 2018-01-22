package com.carin.doninelli.dex.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Names internal constructor(

        @JsonProperty("fr")
        val french: String,

        @JsonProperty("de")
        val german: String,

        @JsonProperty("it")
        val italian: String,

        @JsonProperty("en")
        val english: String

) : Serializable