package com.jedev.mempool.di

import com.jedev.mempool.ui.features.listRankingNodes.ListRankingLightningViewModel
import com.jedev.mempool.ui.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { ListRankingLightningViewModel(get()) }
}