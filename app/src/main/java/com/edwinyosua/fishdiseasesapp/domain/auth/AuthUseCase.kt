package com.edwinyosua.fishdiseasesapp.domain.auth

import android.util.Log
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Login
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun login(email: String, pass: String): Flow<ApiResult<Login>>

    fun register(name: String, email: String, pass: String): Flow<ApiResult<Login>>
}