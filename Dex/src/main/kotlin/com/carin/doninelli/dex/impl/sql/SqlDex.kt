package com.carin.doninelli.dex.impl.sql

import com.carin.doninelli.dex.Dex
import com.carin.doninelli.dex.entities.Pokemon
import java.sql.DriverManager

class SqlDex : Dex {

    companion object {
        private val jdbcUrl: String
        init {
            val dbResourcePath = this::class.java.classLoader.getResource("pokedex.sqlite").path
            jdbcUrl = "jdbc:sqlite:$dbResourcePath"
        }
    }

    override fun searchPokemon(name: String): Pokemon? = DriverManager.getConnection(jdbcUrl).use { connection ->
        DexDao(connection).getPokemon(1)
    }
}

fun main(args: Array<String>) {
    SqlDex().searchPokemon("")
}