package com.jedev.mempool.domain.repositories

import com.jedev.mempool.domain.model.NodeConnectivityModel

interface MemPoolRepository {
    suspend fun getRankingNodeConnectivity(): List<NodeConnectivityModel>
}