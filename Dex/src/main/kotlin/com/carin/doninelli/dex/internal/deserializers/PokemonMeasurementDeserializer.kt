package com.carin.doninelli.dex.internal.deserializers

import com.carin.doninelli.dex.entities.pokemon.PokemonMeasurement
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

internal class PokemonMeasurementDeserializer : JsonDeserializer<PokemonMeasurement>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): PokemonMeasurement {
        val measurement = p.valueAsString
        val (amount, unit) = measurement.split("\\s+".toRegex(), 2)

        return PokemonMeasurement(amount.toDouble(), unit)
    }
}