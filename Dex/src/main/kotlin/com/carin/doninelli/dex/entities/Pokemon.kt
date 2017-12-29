package com.carin.doninelli.dex.entities

import com.carin.doninelli.dex.deserializers.AbilityArrayDeserializer
import com.carin.doninelli.dex.deserializers.UnitFactorDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.io.Serializable

data class Pokemon internal constructor(
        val id: Int,

        val name: String,

        @JsonDeserialize(using = UnitFactorDeserializer::class)
        val height: Double,

        @JsonDeserialize(using = UnitFactorDeserializer::class)
        val weight: Double,

        @JsonDeserialize(using = AbilityArrayDeserializer::class)
        val abilities: List<Ability>
) : Serializable

