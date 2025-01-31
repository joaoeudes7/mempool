package com.jedev.mempool.di

import com.jedev.mempool.domain.useCases.GetRankingNodeUseCase
import com.jedev.mempool.domain.useCases.GetRankingNodeUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    factory<GetRankingNodeUseCase> { GetRankingNodeUseCaseImpl(get()) }
}