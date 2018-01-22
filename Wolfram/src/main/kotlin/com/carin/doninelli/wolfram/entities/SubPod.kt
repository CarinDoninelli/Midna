package com.carin.doninelli.wolfram.entities

import java.io.Serializable

data class SubPod internal constructor(
        val content: List<String>
) : Serializable