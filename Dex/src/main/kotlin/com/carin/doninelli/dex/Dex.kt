package com.carin.doninelli.dex

import com.carin.doninelli.dex.entities.pokemon.Pokemon
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

interface Dex {
    fun searchPokemon(name: String): Pokemon?
}

fun main(args: Array<String>) {
    val dex = DexFactory().createNewDex()
    val mapper = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
    val result = dex.searchPokemon("pikachu")
    println(mapper.writeValueAsString(result))
}
