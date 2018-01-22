package com.carin.doninelli.dex.entities.pokemon

import java.io.Serializable

data class GenderRatios internal constructor(
        val male: Float,
        val female: Float
) : Serializable