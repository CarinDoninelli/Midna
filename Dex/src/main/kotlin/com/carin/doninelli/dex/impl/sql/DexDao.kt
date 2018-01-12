package com.carin.doninelli.dex.impl.sql

import com.carin.doninelli.dex.entities.Pokemon
import com.carin.doninelli.dex.util.fetchQuerySequence
import com.carin.doninelli.dex.util.withStatement
import java.sql.Connection

internal class DexDao(private val connection: Connection) {

    fun getPokemon(id: Int): Pokemon? = connection.withStatement("SELECT * FROM POKEMON_SPECIES_NAMES") {
        fetchQuerySequence { resultSequence ->
            resultSequence.forEach {
                println(it.getString("name"))
            }
        }
        null
    }
}