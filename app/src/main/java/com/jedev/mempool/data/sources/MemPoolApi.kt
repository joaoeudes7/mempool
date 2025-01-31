package com.jedev.mempool.data.sources

import com.jedev.mempool.data.sources.base.HttpClientMemPool
import com.jedev.mempool.data.sources.entities.NodeConnectivityRes
import io.ktor.client.call.body
import io.ktor.client.request.get

class MemPoolApi {
    companion object {
        const val BASE_URL = "https://mempool.space/api/v1"
    }

    suspend fun getRankingNodeConnectivity() = HttpClientMemPool.get(
        "${BASE_URL}/lightning/nodes/rankings/connectivity"
    ).body<List<NodeConnectivityRes>>()
}