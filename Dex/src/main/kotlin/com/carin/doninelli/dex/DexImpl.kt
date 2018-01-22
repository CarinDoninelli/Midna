package com.carin.doninelli.dex

import com.carin.doninelli.core.loader.ResourceLoader
import com.carin.doninelli.dex.entities.pokemon.Pokemon
import com.carin.doninelli.dex.paths.ResourcePaths
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

internal class DexImpl(private val mapper: ObjectMapper) : Dex {
    override fun searchPokemon(name: String): Pokemon? {
        val sanitizedName = name.replace(".", "").replace("\\s+".toRegex(), "_")
        val pokemonJsonPath = ResourcePaths.getPokemonJsonPath(sanitizedName)

        val loader = ResourceLoader()
        return loader.getResourceAsStream(pokemonJsonPath)?.use(mapper::readValue)
    }
}