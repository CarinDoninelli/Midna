@file:JvmName("DexFactory")

package com.carin.doninelli.dex.factory

import com.carin.doninelli.dex.Dex
import com.carin.doninelli.dex.impl.jdbc.JdbcDex
import com.carin.doninelli.dex.impl.jdbc.SqliteConnectionPool

class DexFactory {
    fun createDex(): Dex = JdbcDex(SqliteConnectionPool)
}