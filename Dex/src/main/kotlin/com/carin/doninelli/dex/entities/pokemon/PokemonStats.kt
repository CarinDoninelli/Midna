package com.carin.doninelli.dex.entities.pokemon

import java.io.Serializable

data class PokemonStats internal constructor(
        val hp: Int,
        val atk: Int,
        val def: Int,
        val spAttack: Int,
        val spDef: Int,
        val speed: Int
) : Serializable