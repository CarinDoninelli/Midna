package com.carin.doninelli.core.loader

import com.carin.doninelli.core.exceptions.MissingResourceException
import java.io.InputStream
import java.net.URL
import java.util.*

class ResourceLoader {

    private val loader = javaClass.classLoader

    fun getUrl(resourceName: String): URL =
            loader.getResource(resourceName) ?: throw MissingResourceException(resourceName)

    fun getProperty(resource: String, property: String): String {
        val properties = Properties()
        getResourceAsStreamOrNull(resource)?.use {
            properties.load(it)
        }

        return properties.getProperty(property) ?: throw MissingResourceException("$resource-$property")
    }

    fun getResourceAsStreamOrNull(resource: String): InputStream? = loader.getResourceAsStream(resource)

    fun getResourceAsStream(resource: String): InputStream =
            getResourceAsStreamOrNull(resource) ?: throw MissingResourceException(resource)
}