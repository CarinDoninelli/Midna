package com.carin.doninelli.dex.deserializers

import com.carin.doninelli.dex.entities.Ability
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

internal class AbilityArrayDeserializer : JsonDeserializer<List<Ability>>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): List<Ability> {
        val list = p.codec.readTree<JsonNode>(p)
        return list.map { node ->
            val isHidden = node["is_hidden"].booleanValue()

            val abilityInfo = node["ability"]
            val abilityName = abilityInfo["name"].textValue()

            Ability(abilityName, isHidden)
        }
    }
}