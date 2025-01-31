package com.jedev.mempool.di

import com.jedev.mempool.data.repository.MemPoolRepositoryImpl
import com.jedev.mempool.data.sources.MemPoolApi
import com.jedev.mempool.domain.repositories.MemPoolRepository
import org.koin.dsl.module

val dataModule = module {
    factory { MemPoolApi() }

    factory<MemPoolRepository> { MemPoolRepositoryImpl(get()) }
}