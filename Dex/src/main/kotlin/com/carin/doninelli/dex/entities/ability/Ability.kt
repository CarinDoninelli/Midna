package com.carin.doninelli.dex.entities.ability

import com.carin.doninelli.dex.entities.Descriptions
import com.carin.doninelli.dex.entities.Names
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Ability internal constructor(

        @JsonProperty("index_number")
        val index: Int,

        val names: Names,

        val descriptions: Descriptions

) : Serializable