package com.carin.doninelli.dex.util

import java.net.HttpURLConnection
import java.net.URL

internal inline fun <T> URL.useHttpUrlConnection(f: (HttpURLConnection) -> T): T {
    val connection: HttpURLConnection = openConnection() as HttpURLConnection

    return try {
        connection.let(f)
    } finally {
        connection.disconnect()
    }
}

internal inline fun <T> HttpURLConnection.ifSuccess(f: (HttpURLConnection) -> T): T? {
    return if (responseCode == 200) f(this) else null
}