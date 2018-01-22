package com.carin.doninelli.dex.entities.move

import com.fasterxml.jackson.annotation.JsonProperty

enum class Category {

    @JsonProperty("physical")
    Physical,

    @JsonProperty("status")
    Status,

    @JsonProperty("special")
    Special,

    @JsonProperty("varies")
    Varies
}