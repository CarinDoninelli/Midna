package com.carin.doninelli.dex.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

internal class UnitFactorDeserializer : JsonDeserializer<Double>() {
    private val factor = 10

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Double = p.valueAsDouble / factor
}
