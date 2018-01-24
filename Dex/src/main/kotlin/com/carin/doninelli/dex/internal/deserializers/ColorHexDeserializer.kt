package com.carin.doninelli.dex.internal.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.awt.Color

internal class ColorHexDeserializer : JsonDeserializer<Color>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Color {
        val hex = p.valueAsString
        return Color.decode(hex)
    }
}