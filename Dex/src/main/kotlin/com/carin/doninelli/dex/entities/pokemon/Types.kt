package com.carin.doninelli.dex.entities.pokemon

import com.carin.doninelli.dex.entities.type.Type
import java.io.Serializable

data class Types internal constructor(
        val first: Type,
        val second: Type?
) : Serializable