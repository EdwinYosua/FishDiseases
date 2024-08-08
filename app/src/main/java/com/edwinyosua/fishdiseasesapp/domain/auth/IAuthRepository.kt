package com.edwinyosua.fishdiseasesapp.domain.auth

import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Login
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun login(email: String, pass: String): Flow<ApiResult<Login>>
}