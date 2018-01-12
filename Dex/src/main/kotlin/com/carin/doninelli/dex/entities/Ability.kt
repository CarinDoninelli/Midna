package com.carin.doninelli.dex.entities

import java.io.Serializable

data class Ability internal constructor(
        val id: Int,
        val name: String,
        val isHidden: Boolean
) : Serializable