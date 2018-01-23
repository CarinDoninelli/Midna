package com.carin.doninelli.dex.internal

import com.carin.doninelli.dex.Dex
import com.carin.doninelli.dex.entities.ability.Ability
import com.carin.doninelli.dex.entities.move.Move
import com.carin.doninelli.dex.entities.pokemon.Pokemon
import com.carin.doninelli.dex.internal.messages.LogMessage
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory


private const val POKEDEX_PATH = "pokedex"
private const val POKEMON_PATH = "$POKEDEX_PATH/pokemon"
private const val ABILITY_PATH = "$POKEDEX_PATH/ability"
private const val MOVE_PATH = "$POKEDEX_PATH/move"

internal class DexImpl(private val mapper: ObjectMapper) : Dex {
    private val log = LoggerFactory.getLogger(DexImpl::class.java)

    private val loader = javaClass.classLoader

    override fun searchPokemon(name: String): Pokemon? {
        log.debug(LogMessage.POKEMON_SEARCH_CALLED.value, name)
        val sanitizedName = name.toLowerCase()
                .replace("\\.?[\\s\\-]+".toRegex(), "_")
        log.debug(LogMessage.POKEMON_NAME_SANITIZED.value, sanitizedName)

        val pokemonJsonPath = "$POKEMON_PATH/$sanitizedName.json"

        return loader.getResourceAsStream(pokemonJsonPath)?.use(mapper::readValue)
    }

    override fun searchAbility(name: String): Ability? {
        log.debug(LogMessage.ABILITY_SEARCH_CALLED.value, name)
        val sanitizedName = name.toLowerCase()
                .replace("[\\s\\-]+".toRegex(), "_")
        log.debug(LogMessage.ABILITY_NAME_SANITIZED.value, sanitizedName)

        val abilityJsonPath ="$ABILITY_PATH/$sanitizedName.json"

        return loader.getResourceAsStream(abilityJsonPath)?.use(mapper::readValue)
    }

    override fun searchMove(name: String): Move? {
        log.debug(LogMessage.MOVE_SEARCH_CALLED.value, name)
        val sanitizedName = name.toLowerCase()
                .replace("[\\s\\-]+".toRegex(), "_")
        log.debug(LogMessage.MOVE_NAME_SANITIZED.value, sanitizedName)

        val moveJsonPath = "$MOVE_PATH/$sanitizedName.json"

        return loader.getResourceAsStream(moveJsonPath)?.use(mapper::readValue)
    }
}