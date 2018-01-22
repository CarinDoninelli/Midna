package com.carin.doninelli.dex

import com.carin.doninelli.dex.entities.ability.Ability
import com.carin.doninelli.dex.entities.pokemon.Pokemon

interface Dex {
    fun searchPokemon(name: String): Pokemon?

    fun searchAbility(name: String): Ability?
}

fun main(args: Array<String>) {
    val dex = DexFactory().createDex()
    println(dex.searchPokemon("charmeleon"))
}