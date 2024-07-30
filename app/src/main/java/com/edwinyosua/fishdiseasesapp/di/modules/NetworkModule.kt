package com.edwinyosua.fishdiseasesapp.di.modules

import com.edwinyosua.fishdiseasesapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

val loggingInterceptor = if (BuildConfig.DEBUG) {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
} else {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
}


