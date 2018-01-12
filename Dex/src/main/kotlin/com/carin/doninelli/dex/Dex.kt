@file:JvmName("DexFactory")

package com.carin.doninelli.dex

import com.carin.doninelli.dex.entities.Pokemon

interface Dex {
    fun searchPokemon(name: String): Pokemon?
}
