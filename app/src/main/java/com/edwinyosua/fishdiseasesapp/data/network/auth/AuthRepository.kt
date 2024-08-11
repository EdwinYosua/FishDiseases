package com.edwinyosua.fishdiseasesapp.data.network.auth

import com.edwinyosua.fishdiseasesapp.data.local.SettingPreference
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.data.network.response.ErrorResponse
import com.edwinyosua.fishdiseasesapp.di.modules.networkModule
import com.edwinyosua.fishdiseasesapp.domain.auth.IAuthRepository
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Login
import com.edwinyosua.fishdiseasesapp.domain.auth.mapper.toDomain
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import retrofit2.HttpException

class AuthRepository(
    private val authServices: AuthServices,
    private val dataStore: SettingPreference
) : IAuthRepository {
    override fun login(email: String, pass: String): Flow<ApiResult<Login>> = flow {
        try {
            emit(ApiResult.Loading)
            val response = authServices.login(email, pass)
            if (!response.error) {
//                val loginResponse = response // save response to SettingPreferences later
                dataStore.saveUserLoginData(response.userId)

                //koin procedure to get the module
                unloadKoinModules(networkModule)
                loadKoinModules(networkModule)

                emit(ApiResult.Success(response.toDomain()))
            }

        } catch (e: HttpException) {
            e.printStackTrace()

            //get the error message from API
            val errBody = e.response()?.errorBody()?.string()
            val errResponse = errBody?.let {
                Gson().fromJson<ErrorResponse>(it, object : TypeToken<ErrorResponse>() {}.type)
            }

            //emit the error message from API
            val errMsg = errResponse?.message ?: e.message()
            emit(ApiResult.Error(errMsg))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message.toString()))
        }
    }


}