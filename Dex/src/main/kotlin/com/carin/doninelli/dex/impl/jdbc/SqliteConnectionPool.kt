package com.carin.doninelli.dex.impl.jdbc

import com.carin.doninelli.dex.loader.ResourceLoader
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.LoggerFactory
import java.sql.Connection

internal object SqliteConnectionPool {
    private val log = LoggerFactory.getLogger(javaClass)

    private val dbFileName = "pokedex.sqlite"

    private const val driver = "org.sqlite.JDBC"

    private val jdbcUrl: String by lazy {
        val loader = ResourceLoader()
        val dbPath = loader[dbFileName].path
        "jdbc:sqlite:$dbPath"
    }

    private val pool: ComboPooledDataSource = ComboPooledDataSource().also { pool ->
        pool.driverClass = driver
        pool.jdbcUrl = jdbcUrl

        pool.initialPoolSize = 5
        pool.minPoolSize = 5
        pool.acquireIncrement = 5
        pool.maxPoolSize = 20
    }

    fun getConnection(): Connection {
        log.info("Opened connection on $jdbcUrl")
        return pool.connection
    }
}