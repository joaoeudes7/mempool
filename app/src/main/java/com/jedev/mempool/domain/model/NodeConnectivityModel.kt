package com.jedev.mempool.domain.model

import com.jedev.mempool.utils.Formatters

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
        get() = Formatters.formatDate(updatedAt)

    val firstSeenFormatted: String
        get() = Formatters.formatDate(firstSeen)

    val capacityFormatted: String
        get() = Formatters.formatAmountSats(capacity)

    val channelsFormatted: String
        get() {
            val channelsK = channels / 1000.0

            return if (channelsK > 1) {
                "%.2f".format(channelsK) + " K"
            } else {
                channels.toString()
            }
        }
}