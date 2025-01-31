package com.jedev.mempool.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

object DateUtils {
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