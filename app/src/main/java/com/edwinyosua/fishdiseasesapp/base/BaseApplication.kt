package com.edwinyosua.fishdiseasesapp.base

import android.app.Application
import com.edwinyosua.fishdiseasesapp.di.modules.authModule
import com.edwinyosua.fishdiseasesapp.di.modules.networkModule
import com.edwinyosua.fishdiseasesapp.di.modules.viewModelModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                authModule,
                networkModule,
                viewModelModule
            )
        }
    }
}