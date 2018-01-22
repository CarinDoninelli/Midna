package com.carin.doninelli.midna.bot.messages;

public enum LogMessage {

    POKEMON_WITH_QUERY_FOUND("Pokemon with query {} found. {}"),

    COMMAND_CALLED("{} command called."),

    COMMAND_EXECUTED("{} command executed in {} ms"),

    COMMAND_REGISTERED("{} command registered."),

    POKEMON_SPRITE("Pokemon with name '{}' and sprite '{}'.");

    private final String value;

    LogMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
