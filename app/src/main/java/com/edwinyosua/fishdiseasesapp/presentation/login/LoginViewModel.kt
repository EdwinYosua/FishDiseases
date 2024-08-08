package com.edwinyosua.fishdiseasesapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.data.network.response.LoginResponse
import com.edwinyosua.fishdiseasesapp.domain.auth.AuthUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _loginResult = MutableLiveData<ApiResult<LoginResponse>>()
    val loginResult: LiveData<ApiResult<LoginResponse>> by lazy { _loginResult }

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            authUseCase.login(email, pass).collect {
                _loginResult.value = it
            }
        }
    }

}