package com.carin.doninelli.dex.entities.pokemon

import java.io.Serializable

data class PokemonMeasurement internal constructor(
        val value: Double,
        val unit: String
) : Serializable