package com.edwinyosua.fishdiseasesapp.domain

import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.data.network.response.LoginResponse
import com.edwinyosua.fishdiseasesapp.data.network.response.LoginResult
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun login(auth: LoginResult): Flow<ApiResult<LoginResponse>>
}