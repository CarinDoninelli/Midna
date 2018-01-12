package com.carin.doninelli.dex.entities

import java.io.Serializable

data class Type internal constructor(
    val id: Int,
    val name: String
) : Serializable