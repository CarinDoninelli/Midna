package com.carin.doninelli.dex.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.awt.Color

internal class ColorDeserializer : JsonDeserializer<Color>() {

    private val defaultColor = Color.black

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Color {
        val color = p.valueAsString

        val colors = Class.forName("java.awt.Color").fields

        return colors.firstOrNull { it.name.equals(color, ignoreCase = true) }
                       ?.let { it.get(null) as? Color }
               ?: defaultColor
    }
}