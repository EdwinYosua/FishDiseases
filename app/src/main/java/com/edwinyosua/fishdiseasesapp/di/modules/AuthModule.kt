package com.edwinyosua.fishdiseasesapp.di.modules

import com.edwinyosua.fishdiseasesapp.data.network.auth.AuthRepoImpl
import com.edwinyosua.fishdiseasesapp.domain.IAuthRepository
import org.koin.dsl.module


val authModule = module {
    single<IAuthRepository> { AuthRepoImpl(get()) }
}