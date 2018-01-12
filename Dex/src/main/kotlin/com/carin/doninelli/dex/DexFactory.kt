@file:JvmName("DexFactory")

package com.carin.doninelli.dex

import com.carin.doninelli.dex.impl.DexApi

class DexFactory {
    fun newDex(): Dex = DexApi()
}