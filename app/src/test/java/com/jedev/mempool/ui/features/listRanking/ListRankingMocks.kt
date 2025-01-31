package com.jedev.mempool.ui.features.listRanking

import com.jedev.mempool.domain.model.NodeConnectivityModel

object ListRankingMocks {
    val listNodes = listOf(
        NodeConnectivityModel(
            alias = "node1",
            channels = 2908,
            capacity = 62496865516,
            firstSeen = 1522941222,
            country = "United States",
            city = "San Francisco",
            publicKey = "03864ef025fde8fb587d989186ce6a4a186895ee44a926bfc370e2c366597a3f8f",
            updatedAt = 1661274935,
        ),
    )
}