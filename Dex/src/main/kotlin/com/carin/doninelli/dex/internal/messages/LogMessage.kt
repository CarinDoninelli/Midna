package com.carin.doninelli.dex.internal.messages

internal enum class LogMessage(val value: String) {
    POKEMON_SEARCH_CALLED("Pokemon search called with name '{}'"),

    POKEMON_NAME_SANITIZED("Pokemon name sanitized to '{}'"),

    ABILITY_SEARCH_CALLED("Ability search called with name '{}'"),

    ABILITY_NAME_SANITIZED("Ability name sanitized to '{}'"),

    MOVE_SEARCH_CALLED("Move search called with name '{}'"),

    MOVE_NAME_SANITIZED("Move name sanitized to '{}'"),

    TYPE_INFO_LOOKUP_CALLED("Type {} info lookup called.")
}