package com.carin.doninelli.dex.impl.jdbc

import com.carin.doninelli.dex.Dex
import com.carin.doninelli.dex.entities.Pokemon

class JdbcDex : Dex {

    private val connectionPool = SqliteConnectionPool

    override fun searchPokemon(id: Int): Pokemon? = connectionPool.getConnection().use { connection ->
        DexDao(connection).getPokemonById(id)
    }

    override fun searchPokemon(name: String): Pokemon? = connectionPool.getConnection().use { connection ->
        DexDao(connection).getPokemonByName(name)
    }
}