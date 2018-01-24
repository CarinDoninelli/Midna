package com.carin.doninelli.dex.entities.type

import com.carin.doninelli.dex.entities.Names
import com.carin.doninelli.dex.internal.deserializers.ColorHexDeserializer
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.awt.Color
import java.io.Serializable

data class TypeInfo internal constructor(

        val names: Names,

        @JsonProperty("effectivness")
        val effectiveness: Map<Type, Double>,

        @JsonDeserialize(using = ColorHexDeserializer::class)
        val color: Color

) : Serializable