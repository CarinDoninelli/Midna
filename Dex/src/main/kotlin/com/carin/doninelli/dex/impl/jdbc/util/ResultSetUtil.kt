package com.carin.doninelli.dex.impl.jdbc.util

import java.sql.ResultSet

internal fun ResultSet.asSequence(): Sequence<ResultSet> = generateSequence {
    if (next()) this
    else null
}