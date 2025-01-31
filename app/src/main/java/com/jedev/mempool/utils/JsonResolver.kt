package com.jedev.mempool.utils

import kotlinx.serialization.json.Json

val JsonResolver = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
    encodeDefaults = true
    allowStructuredMapKeys = true
}