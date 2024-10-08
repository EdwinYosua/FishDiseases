package com.edwinyosua.fishdiseasesapp.domain.auth

import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Login
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Register
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class AuthInteractor(private val authRepository: IAuthRepository) : AuthUseCase {

    override fun login(email: String, pass: String): Flow<ApiResult<Login>> {
        return authRepository.login(email, pass).flowOn(Dispatchers.IO)
    }

    override fun register(name: String, email: String, pass: String): Flow<ApiResult<Register>> {
        return authRepository.register(name, email, pass).flowOn(Dispatchers.IO)
    }

}