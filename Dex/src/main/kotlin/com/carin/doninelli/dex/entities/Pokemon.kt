package com.carin.doninelli.dex.entities

import java.io.Serializable

data class Pokemon internal constructor(
        val id: Int,
        val name: String,
        val height: Double,
        val weight: Double,
        val abilities: List<Ability>,
        val types: List<Type>
) : Serializable

