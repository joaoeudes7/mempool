package com.jedev.mempool.data.sources.base

import com.jedev.mempool.utils.JsonResolver
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

val HttpClientMemPool = HttpClient(CIO) {
    expectSuccess = true

    install(HttpCache)

    install(ContentNegotiation) {
        json(JsonResolver)
    }
}