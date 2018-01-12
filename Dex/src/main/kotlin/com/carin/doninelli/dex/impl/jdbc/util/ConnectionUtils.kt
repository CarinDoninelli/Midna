package com.carin.doninelli.dex.impl.jdbc.util

import java.sql.Connection
import java.sql.PreparedStatement

internal inline fun <R> Connection.withStatement(sql: String, action: PreparedStatement.() -> R): R {
    return prepareStatement(sql).use(action)
}