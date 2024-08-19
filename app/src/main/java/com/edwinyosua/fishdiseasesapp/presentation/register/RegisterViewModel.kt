package com.edwinyosua.fishdiseasesapp.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.domain.auth.AuthUseCase
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Register
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepository: AuthUseCase) : ViewModel() {

    private val _registerResult = MutableLiveData<ApiResult<Register>>()
    val registerResult: LiveData<ApiResult<Register>> by lazy { _registerResult }


    fun register(name: String, email: String, pass: String) {
        viewModelScope.launch {
            authRepository.register(name, email, pass).collect {
                _registerResult.value = it
            }
        }
    }


}