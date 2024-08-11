package com.edwinyosua.fishdiseasesapp.di.modules

import com.edwinyosua.fishdiseasesapp.presentation.login.LoginViewModel
import com.edwinyosua.fishdiseasesapp.presentation.onboarding.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { OnBoardingViewModel(get()) }
}