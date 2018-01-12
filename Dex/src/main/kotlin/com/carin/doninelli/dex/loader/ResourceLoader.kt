package com.carin.doninelli.dex.loader

import java.net.URL

internal class ResourceLoader {
    operator fun get(resourceName: String): URL {
        return javaClass.classLoader.getResource(resourceName) ?:
               throw RuntimeException("$resourceName resource not found.")
    }
}