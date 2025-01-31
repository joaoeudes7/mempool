package com.jedev.mempool.ui.features.listRanking

import com.jedev.mempool.MainDispatcherRule
import com.jedev.mempool.domain.useCases.GetRankingNodeUseCase
import com.jedev.mempool.ui.features.listRankingNodes.ListRankingLightningViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ListRankingLightningViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getRankingNodeUseCaseMock = mockk<GetRankingNodeUseCase>()

    private val viewModel = ListRankingLightningViewModel(
        getRankingNodeUseCase = getRankingNodeUseCaseMock
    )

    @Test
    fun `should return list nodes`() = runTest {
        // given
        coEvery { getRankingNodeUseCaseMock() } returns flow {
            delay(100)
            emit(ListRankingMocks.listNodes)
        }

        // when
        viewModel.fetchData(true)
        assert(viewModel.uiState.isRefreshing == true)

        delay(100)

        // then
        assert(viewModel.uiState.isRefreshing == false)
        assert(viewModel.uiState.nodes.isNotEmpty())
    }


}