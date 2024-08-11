package com.edwinyosua.fishdiseasesapp.base

import android.app.Application
import com.edwinyosua.fishdiseasesapp.di.modules.authModule
import com.edwinyosua.fishdiseasesapp.di.modules.dataStoreModule
import com.edwinyosua.fishdiseasesapp.di.modules.networkModule
import com.edwinyosua.fishdiseasesapp.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    dataStoreModule,
                    authModule,
                    networkModule,
                    viewModelModule
                )
            )
        }
    }
}