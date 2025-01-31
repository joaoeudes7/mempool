package com.jedev.mempool.ui.features.listRankingNodes

import androidx.compose.runtime.*
import com.jedev.mempool.domain.model.NodeConnectivityModel

class ListRankingLightningState {
    var nodes by mutableStateOf(emptyList<NodeConnectivityModel>())
    var isRefreshing by mutableStateOf(false)
}

data class ListRankingLightningUiActions(
    val onRefresh: () -> Unit,
    val onCopyPk: (String) -> Unit
)