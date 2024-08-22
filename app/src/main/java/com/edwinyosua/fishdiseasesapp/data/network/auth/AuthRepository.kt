package com.edwinyosua.fishdiseasesapp.data.network.auth

import android.util.Log
import com.edwinyosua.fishdiseasesapp.data.local.SettingPreference
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.data.network.response.ErrorResponse
import com.edwinyosua.fishdiseasesapp.domain.auth.IAuthRepository
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Login
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Register
import com.edwinyosua.fishdiseasesapp.domain.auth.mapper.toDomain
import com.edwinyosua.fishdiseasesapp.utils.ConstVal
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
            if (response.error != true) {
//                val loginResponse = response // save response to SettingPreferences later
                response.userId?.let {
                    dataStore.saveUserLoginData(it)
                    Log.d("TokenCheck", "AuthRepository : $it")
                }

                //koin procedure to get the module
                reloadKoin()
                emit(ApiResult.Success(response.toDomain()))
            }

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(ApiResult.Error(printApiErrorMsg(e)))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message.toString()))
        }
    }

    override fun register(name: String, email: String, pass: String): Flow<ApiResult<Register>> =
        flow {
            try {
                emit(ApiResult.Loading)
                val response = authServices.register(name, email, pass)
                if (response.error != true) {

                    reloadKoin()
                    emit(ApiResult.Success(response.toDomain()))
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiResult.Error(printApiErrorMsg(e)))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error(e.message.toString()))

            }
        }


    private fun printApiErrorMsg(e: HttpException): String {

        //get the error message from API
        val errorBody = e.response()?.errorBody()?.string()
        val errResponse = errorBody?.let {
            Gson().fromJson<ErrorResponse>(it, object : TypeToken<ErrorResponse>() {}.type)
        }

        //emit the error message from API
        return errResponse?.message ?: e.message()
    }

    private fun reloadKoin() {
        //koin procedure to get the module
        unloadKoinModules(ConstVal.allModules)
        loadKoinModules(ConstVal.allModules)
    }


}