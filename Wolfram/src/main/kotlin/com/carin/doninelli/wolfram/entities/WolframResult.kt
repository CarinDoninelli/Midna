package com.carin.doninelli.wolfram.entities

import java.io.Serializable

sealed class WolframResult : Serializable {
    data class Success internal constructor(val pods: List<Pod>) : WolframResult()

    object Failure : WolframResult() {
        val message = "Query was not understood; no results available."

        override fun toString(): String = "Failure(message='$message')"
    }
}