package com.edwinyosua.fishdiseasesapp.utils

import com.edwinyosua.fishdiseasesapp.BuildConfig
import com.edwinyosua.fishdiseasesapp.di.modules.authModule
import com.edwinyosua.fishdiseasesapp.di.modules.dataStoreModule
import com.edwinyosua.fishdiseasesapp.di.modules.modelModule
import com.edwinyosua.fishdiseasesapp.di.modules.networkModule
import com.edwinyosua.fishdiseasesapp.di.modules.viewModelModule


object ConstVal {

    val allModules = listOf(
        authModule,
        dataStoreModule,
        modelModule,
        networkModule,
        viewModelModule
    )

    const val AUTH_URL = BuildConfig.AUTH_URL
    const val MODEL_URL = BuildConfig.MODEL_URL


    const val FILE_NAME_FORMAT = "yyyyMMdd_HHmmss"
    const val MAX_SIZE = 1000000

}