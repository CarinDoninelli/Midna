package com.carin.doninelli.wolfram

import com.carin.doninelli.wolfram.entities.WolframResult

interface Wolfram {
    fun evaluateQuery(query: String): WolframResult
}