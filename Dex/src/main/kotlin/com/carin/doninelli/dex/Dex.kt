package com.carin.doninelli.dex

import com.carin.doninelli.dex.entities.Pokemon
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL
import java.util.concurrent.CompletableFuture

class Dex {
    companion object {
        private val endpoint = "https://pokeapi.co/api/v2/pokemon"
    }

    private val mapper = jacksonObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun searchPokemon(name: String): CompletableFuture<Pokemon?> = CompletableFuture.supplyAsync {
        val url = URL("$endpoint/$name/")
        url.useHttpUrlConnection { connection ->
            connection.requestMethod = "GET"
            connection.setRequestProperty("User-Agent", "Mozilla/5.0")

            connection.ifSuccess { mapper.readValue<Pokemon>(it.inputStream) }
        }
    }
}

fun main(args: Array<String>) {
    val dex = Dex()
    println(dex.searchPokemon("bulbasaur").join())
}
