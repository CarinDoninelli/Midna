package com.carin.doninelli.dex.internal.deserializers

import com.carin.doninelli.dex.entities.pokemon.PokemonTypes
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

internal class PokemonTypesDeserializer : JsonDeserializer<PokemonTypes>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): PokemonTypes {
        val node = p.codec.readTree<JsonNode>(p)
        return PokemonTypes(
                first = node[0].textValue(),
                second = node[1]?.textValue()
        )
    }
}