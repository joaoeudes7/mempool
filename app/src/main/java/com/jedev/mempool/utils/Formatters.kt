package com.jedev.mempool.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

object Formatters {
    fun formatAmountSats(amount: Long): String {
        val capacityInBtc = amount / 1000_000_000.0

        return if (capacityInBtc > 0.00000001) {
            "%.8f".format(capacityInBtc) + " BTC"
        } else {
            val capacityInSat = amount / 1000.0
            "%.2f".format(capacityInSat) + " sats"
        }
    }

    @OptIn(FormatStringsInDatetimeFormats::class)
    fun formatDate(
        date: Long,
        formatPattern: String = "dd/MM HH:mm:ss"
    ): String {
        Instant.fromEpochMilliseconds(date).toLocalDateTime(TimeZone.UTC).let {
            val dateTimeFormat = LocalDateTime.Format {
                byUnicodePattern(formatPattern)
            }

            return dateTimeFormat.format(it)
        }
    }
}