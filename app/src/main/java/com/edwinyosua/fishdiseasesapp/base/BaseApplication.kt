package com.edwinyosua.fishdiseasesapp.base

import android.app.Application
import com.edwinyosua.fishdiseasesapp.utils.ConstVal
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                ConstVal.allModules
            )
        }
    }
}