package com.carin.doninelli.dex.entities.ability

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Descriptions internal constructor(

        @JsonProperty("en")
        val english: String,

        @JsonProperty("de")
        val german: String

) : Serializable