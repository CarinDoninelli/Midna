package com.carin.doninelli.dex.entities

import java.io.Serializable

data class Color internal constructor(
        val id: Int,
        val name: String
) : Serializable