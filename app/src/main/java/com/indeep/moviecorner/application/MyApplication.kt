package com.indeep.moviecorner.application

import android.app.Application
import com.indeep.core.di.databaseModule
import com.indeep.core.di.networkModule
import com.indeep.core.di.repositoryModule
import com.indeep.moviecorner.di.useCaseModule
import com.indeep.moviecorner.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}