package com.carin.doninelli.dex.impl.jdbc

import com.carin.doninelli.dex.entities.Ability
import com.carin.doninelli.dex.entities.Pokemon
import com.carin.doninelli.dex.entities.Type
import com.carin.doninelli.dex.impl.jdbc.util.fetchQuerySequence
import com.carin.doninelli.dex.impl.jdbc.util.withStatement
import java.sql.Connection
import java.sql.ResultSet

internal class DexDao(private val connection: Connection) {

    fun getPokemonById(id: Int): Pokemon? = connection.withStatement(Sql.SELECT_POKEMON_INFO_BY_ID.query) {
        setInt(1, id)

        fetchQuerySequence { resultSequence ->
            resultSequence
                    .map { extractPokemon(it) }
                    .firstOrNull()
        }
    }

    fun getPokemonByName(name: String): Pokemon? = connection.withStatement(Sql.SELECT_POKEMON_INFO_BY_NAME.query) {
        setString(1, name)

        fetchQuerySequence { resultSequence ->
            resultSequence
                    .map { extractPokemon(it) }
                    .firstOrNull()
        }
    }

    private fun getAbilitiesByPokemonId(pokemonId: Int): List<Ability> = connection.withStatement(Sql.SELECT_POKEMON_ABILITIES.query) {
        setInt(1, pokemonId)

        fetchQuerySequence { resultSequence ->
            resultSequence
                    .map { extractAbility(it) }
                    .toList()
        }
    }

    private fun getTypesByPokemonId(pokemonId: Int): List<Type> = connection.withStatement(Sql.SELECT_POKEMON_TYPES.query) {
        setInt(1, pokemonId)

        fetchQuerySequence { resultSequence ->
            resultSequence
                    .map { extractType(it) }
                    .toList()
        }
    }

    private fun extractType(resultSet: ResultSet): Type = Type(
            id = resultSet.getInt("id"),
            name = resultSet.getString("name")
    )

    private fun extractAbility(resultSet: ResultSet): Ability = Ability(
            id = resultSet.getInt("id"),
            name = resultSet.getString("name"),
            isHidden = resultSet.getBoolean("is_hidden")
    )

    private fun extractPokemon(resultSet: ResultSet): Pokemon = Pokemon(
            id = resultSet.getInt("id"),
            name = resultSet.getString("name"),
            height = resultSet.getDouble("height"),
            weight = resultSet.getDouble("weight"),
            abilities = getAbilitiesByPokemonId(resultSet.getInt("id")),
            types = getTypesByPokemonId(resultSet.getInt("id"))
    )
}