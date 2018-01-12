package com.carin.doninelli.dex.impl.jdbc


private const val ENGLISH_LANG_ID = 9

internal enum class Sql(val query: String) {
    SELECT_POKEMON_INFO_BY_ID(
            "SELECT " +
            "  sname.name, " +
            "  pokemon.id, " +
            "  pokemon.height, " +
            "  pokemon.weight, " +
            "  pokemon.base_experience " +
            "FROM pokemon " +
            "  JOIN pokemon_species_names AS sname " +
            "    ON sname.pokemon_species_id = pokemon.species_id " +
            "WHERE sname.local_language_id = $ENGLISH_LANG_ID " +
            "      AND pokemon.id = ?"
    ),

    SELECT_POKEMON_INFO_BY_NAME(
            "SELECT " +
            "  sname.name, " +
            "  pokemon.id, " +
            "  pokemon.height, " +
            "  pokemon.weight, " +
            "  pokemon.base_experience " +
            "FROM pokemon " +
            "  JOIN pokemon_species_names AS sname " +
            "    ON sname.pokemon_species_id = pokemon.species_id " +
            "WHERE sname.local_language_id = $ENGLISH_LANG_ID " +
            "      AND sname.name = ?" +
            "COLLATE NOCASE"
    ),

    SELECT_POKEMON_ABILITIES(
            "SELECT " +
            "  abilities.id, " +
            "  a.name, " +
            "  pokemon_abilities.is_hidden " +
            "FROM abilities " +
            "  JOIN pokemon_abilities " +
            "    ON abilities.id = pokemon_abilities.ability_id " +
            "  JOIN pokemon " +
            "    ON pokemon_abilities.pokemon_id = pokemon.id " +
            "  JOIN ability_names a " +
            "    ON abilities.id = a.ability_id " +
            "WHERE pokemon.id = ? " +
            "      AND a.local_language_id = $ENGLISH_LANG_ID " +
            "ORDER BY pokemon_abilities.is_hidden, abilities.id"
    ),

    SELECT_POKEMON_TYPES(
            "SELECT " +
            "  types.id, " +
            "  type_names.name " +
            "FROM types " +
            "  JOIN type_names " +
            "    ON types.id = type_names.type_id " +
            "  JOIN pokemon_types pt " +
            "    ON types.id = pt.type_id " +
            "WHERE pt.pokemon_id = ? " +
            "      AND type_names.local_language_id = $ENGLISH_LANG_ID " +
            "ORDER BY types.id"
    )
}