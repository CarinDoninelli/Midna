@file:JvmName("DexFactory")

package com.carin.doninelli.dex

import com.carin.doninelli.dex.entities.Pokemon
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.Executors

interface Dex {
    fun searchPokemon(name: String): CompletableFuture<Pokemon?> {
        val executor = Executors.newSingleThreadExecutor()
        val search = searchPokemon(name, executor)
        executor.shutdown()
        return search
    }

    fun searchPokemon(name: String, executor: Executor): CompletableFuture<Pokemon?>
}
