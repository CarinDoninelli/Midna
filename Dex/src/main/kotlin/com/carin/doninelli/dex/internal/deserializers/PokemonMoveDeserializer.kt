package com.carin.doninelli.dex.internal.deserializers

import com.carin.doninelli.dex.entities.pokemon.ItemOnParent
import com.carin.doninelli.dex.entities.pokemon.LvlUpMove
import com.carin.doninelli.dex.entities.pokemon.Move
import com.carin.doninelli.dex.entities.pokemon.ParentMove
import com.carin.doninelli.dex.entities.pokemon.TmMove
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

internal class PokemonMoveDeserializer : JsonDeserializer<Move>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Move {
        val node = p.codec.readTree<JsonNode>(p)

        val moveName = node["move"].textValue()
        return when {
            node.has("level") -> LvlUpMove(
                    level = node["level"].intValue(),
                    name = moveName
            )

            node.has("parent") -> ParentMove(
                    parent = node["parent"].textValue(),
                    name = moveName
            )

            node.has("tm") -> TmMove(
                    tm = node["tm"].textValue(),
                    name = moveName
            )

            else -> ItemOnParent(
                    itemOnParent = node["item_on_parent"].textValue(),
                    name = moveName
            )
        }
    }
}