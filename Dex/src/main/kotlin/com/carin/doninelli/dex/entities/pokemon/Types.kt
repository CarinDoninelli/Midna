package com.carin.doninelli.dex.entities.pokemon

import java.io.Serializable

data class Types internal constructor(
        val first: String,
        val second: String?
) : Serializable