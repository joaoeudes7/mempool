package com.jedev.mempool.data.repository

import com.jedev.mempool.data.mappers.toModel
import com.jedev.mempool.data.sources.MemPoolApi
import com.jedev.mempool.domain.model.NodeConnectivityModel
import com.jedev.mempool.domain.repositories.MemPoolRepository

class MemPoolRepositoryImpl(
    private val api: MemPoolApi
) : MemPoolRepository {

    override suspend fun getRankingNodeConnectivity(): List<NodeConnectivityModel> {
        return api.getRankingNodeConnectivity().map { it.toModel() }
    }
}