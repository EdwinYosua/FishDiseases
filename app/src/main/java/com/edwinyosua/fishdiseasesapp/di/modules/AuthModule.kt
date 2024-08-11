package com.edwinyosua.fishdiseasesapp.di.modules

import com.edwinyosua.fishdiseasesapp.data.network.auth.AuthRepository
import com.edwinyosua.fishdiseasesapp.domain.auth.AuthInteractor
import com.edwinyosua.fishdiseasesapp.domain.auth.AuthUseCase
import com.edwinyosua.fishdiseasesapp.domain.auth.IAuthRepository
import org.koin.dsl.module


val authModule = module {

    factory<AuthUseCase> { AuthInteractor(get()) }

    single { AuthInteractor(get()) }

    single<IAuthRepository> { AuthRepository(get(), get()) }

}