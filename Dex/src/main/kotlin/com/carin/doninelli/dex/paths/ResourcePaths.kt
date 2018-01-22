package com.carin.doninelli.dex.paths

internal object ResourcePaths {
    private const val pokedexPath = "pokedex"

    private const val pokemonPath = "$pokedexPath/pokemon"


    fun getPokemonJsonPath(pokemonName: String): String = "$pokemonPath/$pokemonName.json"
}