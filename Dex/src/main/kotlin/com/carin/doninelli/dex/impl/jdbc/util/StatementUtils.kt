package com.carin.doninelli.dex.impl.jdbc.util

import java.sql.PreparedStatement
import java.sql.ResultSet

internal inline fun <R> PreparedStatement.fetchQuerySequence(action: (Sequence<ResultSet>) -> R): R {
    return executeQuery().use { resultSet -> action(resultSet.asSequence()) }
}