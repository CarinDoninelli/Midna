package com.carin.doninelli.dex.internal.deserializers

import com.carin.doninelli.dex.entities.pokemon.ItemOnParentPokemonMove
import com.carin.doninelli.dex.entities.pokemon.LvlUpPokemonMove
import com.carin.doninelli.dex.entities.pokemon.ParentPokemonMove
import com.carin.doninelli.dex.entities.pokemon.PokemonMove
import com.carin.doninelli.dex.entities.pokemon.TmPokemonMove
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

internal class PokemonMoveDeserializer : JsonDeserializer<PokemonMove>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): PokemonMove {
        val node = p.codec.readTree<JsonNode>(p)

        val moveName = node["move"].textValue()
        return when {
            node.has("level") -> LvlUpPokemonMove(
                    level = node["level"].intValue(),
                    name = moveName
            )

            node.has("parent") -> ParentPokemonMove(
                    parent = node["parent"].textValue(),
                    name = moveName
            )

            node.has("tm") -> TmPokemonMove(
                    tm = node["tm"].textValue(),
                    name = moveName
            )

            else -> ItemOnParentPokemonMove(
                    itemOnParent = node["item_on_parent"].textValue(),
                    name = moveName
            )
        }
    }
}