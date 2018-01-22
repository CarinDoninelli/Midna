package com.carin.doninelli.core.loader

import com.carin.doninelli.core.exceptions.MissingResourceException
import java.io.InputStream
import java.net.URL
import java.util.*

class ResourceLoader {
    fun getUrl(resourceName: String): URL {
        val loader = javaClass.classLoader
        return loader.getResource(resourceName) ?: throw MissingResourceException(resourceName)
    }

    fun getProperty(resource: String, property: String): String {
        val properties = Properties()
        getResourceAsStream(resource)?.use {
            properties.load(it)
        }

        return properties.getProperty(property) ?: throw MissingResourceException("$resource-$property")
    }

    fun getResourceAsStream(resource: String): InputStream? = javaClass.classLoader.getResourceAsStream(resource)
}