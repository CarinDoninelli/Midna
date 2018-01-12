@file:JvmName("DexFactory")

package com.carin.doninelli.dex

import com.carin.doninelli.dex.entities.Pokemon

interface Dex {
    fun searchPokemon(id: Int): Pokemon?
    fun searchPokemon(name: String): Pokemon?
}
