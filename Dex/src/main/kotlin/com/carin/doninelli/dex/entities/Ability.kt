package com.carin.doninelli.dex.entities

import java.io.Serializable

data class Ability(
        val name: String,
        val isHidden: Boolean
) : Serializable