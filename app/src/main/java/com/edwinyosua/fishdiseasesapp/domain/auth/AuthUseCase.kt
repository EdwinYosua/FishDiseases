package com.edwinyosua.fishdiseasesapp.domain.auth

import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.data.network.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun login(email: String, pass: String): Flow<ApiResult<LoginResponse>>
}