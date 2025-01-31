package com.jedev.mempool

import android.app.Application
import com.jedev.mempool.di.dataModule
import com.jedev.mempool.di.useCasesModule
import com.jedev.mempool.di.viewModelModule
import org.koin.core.context.startKoin

class MemPoolApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupDi()
    }

    private fun setupDi() {
        startKoin {
            modules(
                dataModule,
                useCasesModule,
                viewModelModule
            )
        }
    }
}