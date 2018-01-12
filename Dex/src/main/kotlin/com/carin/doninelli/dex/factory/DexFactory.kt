@file:JvmName("DexFactory")

package com.carin.doninelli.dex.factory

import com.carin.doninelli.dex.Dex
import com.carin.doninelli.dex.impl.sql.SqlDex

class DexFactory {
    fun newDex(): Dex = SqlDex()
}