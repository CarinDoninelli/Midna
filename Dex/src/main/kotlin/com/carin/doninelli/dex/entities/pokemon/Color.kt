package com.carin.doninelli.dex.entities.pokemon

import com.fasterxml.jackson.annotation.JsonIgnore

enum class Color(val r: Int, val g: Int, val b: Int) {
    Pink(255, 192, 203),

    Green(0, 255, 0),

    Blue(0, 0, 255),

    Purple(147, 112, 219),

    Gray(169, 169, 169),

    Brown(205, 133, 63),

    Black(0, 0, 0),

    Yellow(255, 255, 0),

    White(255, 255, 255),

    Red(255, 0, 0);

    @JsonIgnore
    val awt: java.awt.Color = java.awt.Color(r, g, b)
}