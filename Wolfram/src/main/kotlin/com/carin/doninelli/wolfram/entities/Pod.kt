package com.carin.doninelli.wolfram.entities

import java.io.Serializable

data class Pod internal constructor(
        val title: String,
        val subPods: List<SubPod>
) : Serializable