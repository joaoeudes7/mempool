package com.jedev.mempool.ui.features.listRankingNodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedev.mempool.domain.useCases.GetRankingNodeUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListRankingLightningViewModel(
    private val getRankingNodeUseCase: GetRankingNodeUseCase
) : ViewModel() {

    val uiState = ListRankingLightningState()

    val events = MutableSharedFlow<String>()

    fun fetchData(fromRefresh: Boolean = false) = viewModelScope.launch {
        uiState.isRefreshing = fromRefresh

        getRankingNodeUseCase.invoke()
            .catch {
                events.emit(it.message ?: "Error")
            }
            .collect {
                uiState.apply {
                    isRefreshing = false
                    nodes = it
                }
            }
    }

    fun onCopyPk(content: String) {

    }

}