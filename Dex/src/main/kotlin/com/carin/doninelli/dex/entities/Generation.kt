package com.carin.doninelli.dex.entities

import com.fasterxml.jackson.annotation.JsonProperty

enum class Generation {
    Red,
    Blue,
    Yellow,
    Gold,
    Silver,
    Crystal,
    Ruby,
    Sapphire,
    Emerald,
    FireRed,
    LeafGreen,
    Diamond,
    Pearl,
    Platinum,
    HeartGold,
    SoulSilver,
    Black,
    White,

    @JsonProperty("Black 2")
    Black2,

    @JsonProperty("White 2")
    White2,

    X,
    Y,

    @JsonProperty("Omega Ruby")
    OmegaRuby,

    @JsonProperty("Alpha Sapphire")
    AlphaSapphire,

    Sun,
    Moon
}