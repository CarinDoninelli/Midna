package com.carin.doninelli.midna.bot.messages;

public enum LogMessage {

    POKEMON_WITH_QUERY_FOUND("Pokemon with query '{}' found. {}"),

    COMMAND_CALLED("{} command called."),

    COMMAND_EXECUTED("{} command executed in {} ms"),

    COMMAND_REGISTERED("{} command registered."),

    POKEMON_SPRITE("Pokemon with name '{}' and sprite '{}'."),

    INVALID_DEX_SUB_COMMAND("Invalid dex sub command '{}'."),

    ABILITY_WITH_QUERY_FOUND("Ability with query '{}' found. {}"),

    MOVE_WITH_QUERY_FOUND("Move with query '{}' found. {}");

    private final String value;

    LogMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
