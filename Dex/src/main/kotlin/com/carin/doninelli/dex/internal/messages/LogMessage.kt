package com.carin.doninelli.dex.internal.messages

internal enum class LogMessage(val value: String) {
    POKEMON_SEARCH_CALLED("Pokemon search called with name '{}'"),

    POKEMON_NAME_SANITIZED("Pokemon name sanitized to '{}'"),

    ABILITY_SEARCH_CALLED("Ability search called with name '{}'"),

    ABILITY_NAME_SANITIZED("Ability name sanitized to '{}'")
}