package com.carin.doninelli.wolfram.internal

import com.carin.doninelli.wolfram.Wolfram
import com.carin.doninelli.wolfram.entities.WolframResult
import com.carin.doninelli.wolfram.exceptions.WolframException
import com.carin.doninelli.wolfram.internal.mappings.mapPodArray
import com.wolfram.alpha.WAEngine
import com.wolfram.alpha.WAException

internal class DefaultWolfram(private val engine: WAEngine) : Wolfram {

    override fun evaluateQuery(query: String): WolframResult = try {
        val waQuery = engine.createQuery(query)
        val queryResult = engine.performQuery(waQuery)

        when {
            queryResult.isSuccess -> WolframResult.Success(queryResult.pods.mapPodArray())

            queryResult.isError -> throw WolframException(queryResult.errorCode, queryResult.errorMessage)

            else -> WolframResult.Failure
        }
    } catch (ex: WAException) {
        throw WolframException(ex)
    }
}