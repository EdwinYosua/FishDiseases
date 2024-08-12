package com.edwinyosua.fishdiseasesapp.data.network.auth

import com.edwinyosua.fishdiseasesapp.data.network.response.LoginResponse
import com.edwinyosua.fishdiseasesapp.data.network.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthServices {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse


    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse
}