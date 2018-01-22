package com.carin.doninelli.core.exceptions

class MissingResourceException internal constructor(resourceName: String) : RuntimeException("$resourceName resource not found")