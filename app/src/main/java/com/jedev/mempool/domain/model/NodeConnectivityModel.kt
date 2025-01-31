package com.jedev.mempool.domain.model

import com.jedev.mempool.utils.DateUtils

data class NodeConnectivityModel(
    val publicKey: String,
    val alias: String,
    val channels: Long,
    val capacity: Long,
    val firstSeen: Long,
    val updatedAt: Long,
    val city: String? = null,
    val country: String? = null,
) {
    val location: String
        get() = listOf(city, country).filterNotNull().joinToString(", ")

    val updatedAtFormatted: String
        get() = DateUtils.formatDate(updatedAt)

    val firstSeenFormatted: String
        get() = DateUtils.formatDate(firstSeen)

    val capacityFormatted: String
        get() {
            val satsB = capacity / 1000_000_000.0

            return "%.1f".format(satsB) + " B sats"
        }

    val channelsFormatted: String
        get() {
            val channelsK = channels / 1000.0

            return "%.2f".format(channelsK) + " K"
        }
}