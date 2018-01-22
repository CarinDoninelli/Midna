package com.carin.doninelli.wolfram.exceptions

class WolframException : RuntimeException {
    internal constructor(code: Int, message: String) : super("Error $code: $message")
    internal constructor(cause: Throwable?) : super(cause)
}