package com.carin.doninelli.midna.bot.resource;

public enum PropertySource {
    CREDENTIALS("credentials.properties"),
    THUMBNAILS("thumbnails.properties");

    private final String fileName;

    PropertySource(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
