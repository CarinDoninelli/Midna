package com.carin.doninelli.wolfram.factory

import com.carin.doninelli.wolfram.Wolfram
import com.carin.doninelli.wolfram.internal.DefaultWolfram
import com.wolfram.alpha.WAEngine

class WolframFactory {

    private val plainTextFormat = "plaintext"

    fun createWolframClient(appId: String): Wolfram {
        val engine = WAEngine().apply {
            appID = appId
            addFormat(plainTextFormat)
        }

        return DefaultWolfram(engine)
    }
}