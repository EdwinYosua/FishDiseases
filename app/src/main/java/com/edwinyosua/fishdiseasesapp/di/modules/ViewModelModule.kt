package com.edwinyosua.fishdiseasesapp.di.modules

import com.edwinyosua.fishdiseasesapp.presentation.home.HomeViewModel
import com.edwinyosua.fishdiseasesapp.presentation.login.LoginViewModel
import com.edwinyosua.fishdiseasesapp.presentation.onboarding.OnBoardingViewModel
import com.edwinyosua.fishdiseasesapp.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { OnBoardingViewModel(get()) }
}