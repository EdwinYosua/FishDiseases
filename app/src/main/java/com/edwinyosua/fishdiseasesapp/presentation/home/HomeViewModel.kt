package com.edwinyosua.fishdiseasesapp.presentation.home

import androidx.lifecycle.ViewModel
import com.edwinyosua.fishdiseasesapp.domain.IAuthRepository

class HomeViewModel(private val authRepo: IAuthRepository) : ViewModel() {


    fun logout() = authRepo.logout()

}