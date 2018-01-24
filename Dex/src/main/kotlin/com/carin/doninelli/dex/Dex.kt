package com.carin.doninelli.dex

import com.carin.doninelli.dex.entities.ability.Ability
import com.carin.doninelli.dex.entities.move.Move
import com.carin.doninelli.dex.entities.pokemon.Pokemon
import com.carin.doninelli.dex.entities.type.Type
import com.carin.doninelli.dex.entities.type.TypeInfo

interface Dex {
    fun searchPokemon(name: String): Pokemon?

    fun searchAbility(name: String): Ability?

    fun searchMove(name: String): Move?

    fun searchTypeInfo(type: Type): TypeInfo
}