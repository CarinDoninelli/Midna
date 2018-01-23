package com.carin.doninelli.dex.internal.deserializers

import com.carin.doninelli.dex.entities.pokemon.Types
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

internal class PokemonTypesDeserializer : JsonDeserializer<Types>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Types {
        val node = p.codec.readTree<JsonNode>(p)
        return Types(
                first = node[0].textValue(),
                second = node[1]?.textValue()
        )
    }
}