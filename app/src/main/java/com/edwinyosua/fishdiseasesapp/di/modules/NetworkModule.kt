package com.edwinyosua.fishdiseasesapp.di.modules

import com.edwinyosua.fishdiseasesapp.BuildConfig
import com.edwinyosua.fishdiseasesapp.data.network.auth.AuthServices
import com.edwinyosua.fishdiseasesapp.utils.ConstVal
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val loggingInterceptor = if (BuildConfig.DEBUG) {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
} else {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    single(named("auth")) {
        Retrofit.Builder()
            .baseUrl(ConstVal.AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single(named("model")) {
        Retrofit.Builder()
            .baseUrl(ConstVal.MODEL_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { authServicesProvider(get<Retrofit>(named("auth"))) }
}

fun authServicesProvider(retrofit: Retrofit): AuthServices =
    retrofit.create(AuthServices::class.java)





