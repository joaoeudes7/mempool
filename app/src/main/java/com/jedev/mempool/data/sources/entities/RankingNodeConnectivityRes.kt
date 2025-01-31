package com.jedev.mempool.data.sources.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TranslationsName (
    val de: String? = null,
    val en: String,
    val es: String? = null,
    val fr: String? = null,
    val ja: String? = null,

    @SerialName("pt-BR")
    val ptBR: String? = null,

    val ru: String? = null,

    @SerialName("zh-CN")
    val zhCN: String? = null
)

@Serializable
data class NodeConnectivityRes (
    val publicKey: String,
    val alias: String,
    val channels: Long,
    val capacity: Long,
    val firstSeen: Long,
    val updatedAt: Long,
    val city: TranslationsName? = null,
    val country: TranslationsName? = null,

    @SerialName("iso_code")
    val isoCode: String? = null,

    val subdivision: String? = null
)
