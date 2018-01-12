package com.carin.doninelli.dex.impl

import com.carin.doninelli.dex.Dex
import com.carin.doninelli.dex.entities.Pokemon
import com.carin.doninelli.dex.util.ifSuccess
import com.carin.doninelli.dex.util.useHttpUrlConnection
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.Supplier

internal class DexApi : Dex {
    companion object {
        private const val endpoint = "https://pokeapi.co/api/v2/pokemon"
    }

    private val mapper = jacksonObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    override fun searchPokemon(name: String, executor: Executor): CompletableFuture<Pokemon?> {
        return CompletableFuture.supplyAsync(Supplier {
            val url = URL("$endpoint/$name/")
            url.useHttpUrlConnection { connection ->
                connection.requestMethod = "GET"
                connection.setRequestProperty("User-Agent", "Mozilla/5.0")

                connection.ifSuccess { mapper.readValue<Pokemon>(it.inputStream) }
            }
        }, executor)
    }
}