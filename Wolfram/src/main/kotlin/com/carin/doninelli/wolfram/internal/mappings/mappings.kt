package com.carin.doninelli.wolfram.internal.mappings

import com.carin.doninelli.wolfram.entities.Pod
import com.carin.doninelli.wolfram.entities.SubPod
import com.wolfram.alpha.WAPlainText
import com.wolfram.alpha.WAPod
import com.wolfram.alpha.WASubpod


internal fun Array<WAPod>.mapPodArray(): List<Pod> = asSequence()
        .filterNot { it.isError }
        .map { it.mapPod() }
        .filter { it.subPods.isNotEmpty() }
        .toList()

private fun WAPod.mapPod(): Pod {
    val subPods = subpods
            .asSequence()
            .map { it.mapSubPod() }
            .filter { it.content.isNotEmpty() }
            .toList()
    return Pod(title, subPods)
}

private fun WASubpod.mapSubPod(): SubPod {
    val content = contents
            .asSequence()
            .filterIsInstance<WAPlainText>()
            .map { it.text }
            .filter { it.isNotBlank() }
            .toList()
    return SubPod(content)
}