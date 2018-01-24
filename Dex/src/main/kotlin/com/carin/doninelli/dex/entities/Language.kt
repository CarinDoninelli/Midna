package com.carin.doninelli.dex.entities

import com.fasterxml.jackson.annotation.JsonProperty

enum class Language {
    @JsonProperty("en")
    English,

    @JsonProperty("fr")
    French,

    @JsonProperty("de")
    German,

    @JsonProperty("it")
    Italian,

    @JsonProperty("gr")
    Greek,

    @JsonProperty("pl")
    Polish,

    @JsonProperty("dk")
    Danish
}