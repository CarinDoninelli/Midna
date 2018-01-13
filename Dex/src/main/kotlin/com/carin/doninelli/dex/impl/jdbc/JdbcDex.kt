package com.carin.doninelli.dex.impl.jdbc

import com.carin.doninelli.dex.Dex
import com.carin.doninelli.dex.entities.Pokemon

internal class JdbcDex(private val connectionPool: SqliteConnectionPool) : Dex {

    override fun searchPokemon(id: Int): Pokemon? = connectionPool.getConnection().use { connection ->
        DexDao(connection).getPokemonById(id)
    }

    override fun searchPokemon(name: String): Pokemon? = connectionPool.getConnection().use { connection ->
        DexDao(connection).getPokemonByName(name)
    }
}