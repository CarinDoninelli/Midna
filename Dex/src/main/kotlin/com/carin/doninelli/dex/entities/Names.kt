package com.carin.doninelli.dex.entities

data class Names internal constructor(private val names: Map<Language, String>) : Map<Language, String> by names {
    val english get() = names.getValue(Language.English)
}