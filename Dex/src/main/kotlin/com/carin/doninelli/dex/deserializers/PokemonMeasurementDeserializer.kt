package com.carin.doninelli.dex.deserializers

import com.carin.doninelli.dex.entities.pokemon.Measurement
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

internal class PokemonMeasurementDeserializer : JsonDeserializer<Measurement>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Measurement {
        val measurement = p.valueAsString
        val (amount, unit) = measurement.split("\\s+".toRegex(), 2)

        return Measurement(amount.toDouble(), unit)
    }
}