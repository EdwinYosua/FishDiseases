package com.edwinyosua.fishdiseasesapp.data.network.auth

import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.data.network.response.ErrorResponse
import com.edwinyosua.fishdiseasesapp.data.network.response.LoginResponse
import com.edwinyosua.fishdiseasesapp.data.network.response.LoginResult
import com.edwinyosua.fishdiseasesapp.di.modules.networkModule
import com.edwinyosua.fishdiseasesapp.domain.IAuthRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import retrofit2.HttpException

class AuthRepoImpl(
    private val authServices: AuthServices,
//    private val pref : SettingPreference // save response to SettingPreferences later
) : IAuthRepository {
    override fun login(auth: LoginResult): Flow<ApiResult<LoginResponse>> = flow {
        try {
            emit(ApiResult.Loading)
            val response = authServices.login(auth.name, auth.password)
            if (!response.error) {
//                val loginResponse = response // save response to SettingPreferences later
                unloadKoinModules(networkModule)
                loadKoinModules(networkModule)
                emit(ApiResult.Success(response))
            } else emit(ApiResult.Error(response.message))
        } catch (e: HttpException) {
            e.printStackTrace()
            val errBody = e.response()?.errorBody()?.string()
            val errResponse = errBody?.let {
                Gson().fromJson<ErrorResponse>(it, object : TypeToken<ErrorResponse>() {}.type)
            }
            val errMsg = errResponse?.message ?: e.message()
            emit(ApiResult.Error(errMsg))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message.toString()))
        }
    }


}