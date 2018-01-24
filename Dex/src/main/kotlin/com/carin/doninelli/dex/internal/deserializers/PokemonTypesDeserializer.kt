package com.carin.doninelli.dex.internal.deserializers

import com.carin.doninelli.dex.entities.pokemon.PokemonTypes
import com.carin.doninelli.dex.entities.type.Type
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue

internal class PokemonTypesDeserializer : JsonDeserializer<PokemonTypes>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): PokemonTypes {
        val node = p.codec.readTree<JsonNode>(p)
        val mapper = p.codec as ObjectMapper
        return PokemonTypes(
                first = mapper.treeToValue(node[0]),
                second = node[1]?.let { mapper.treeToValue<Type>(it) }
        )
    }
}