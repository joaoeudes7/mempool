package com.jedev.mempool.domain.useCases

import com.jedev.mempool.domain.model.NodeConnectivityModel
import com.jedev.mempool.domain.repositories.MemPoolRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetRankingNodeUseCase {
    operator fun invoke(): Flow<List<NodeConnectivityModel>>
}

class GetRankingNodeUseCaseImpl(
    private val repository: MemPoolRepository
) : GetRankingNodeUseCase {
    override operator fun invoke() = flow {
        emit(repository.getRankingNodeConnectivity())
    }.flowOn(Dispatchers.IO)
}