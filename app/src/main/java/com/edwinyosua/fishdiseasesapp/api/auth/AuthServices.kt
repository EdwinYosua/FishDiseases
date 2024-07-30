package com.edwinyosua.fishdiseasesapp.api.auth

import com.edwinyosua.fishdiseasesapp.api.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthServices {
    @FormUrlEncoded
    @POST("login")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}