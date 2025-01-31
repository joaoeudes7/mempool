package com.jedev.mempool.data.mappers

import com.jedev.mempool.data.sources.entities.NodeConnectivityRes
import com.jedev.mempool.domain.model.NodeConnectivityModel

fun NodeConnectivityRes.toModel() = NodeConnectivityModel(
    city = city?.ptBR,
    channels = channels,
    alias = alias,
    country = country?.ptBR,
    capacity = capacity,
    firstSeen = firstSeen,
    publicKey = publicKey,
    updatedAt = updatedAt
)