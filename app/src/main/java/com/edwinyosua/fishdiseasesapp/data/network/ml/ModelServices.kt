package com.edwinyosua.fishdiseasesapp.data.network.ml

import com.edwinyosua.fishdiseasesapp.data.network.response.ModelResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ModelServices {
    @Multipart
    @POST("predict")
    suspend fun predict(
        @Part file: MultipartBody.Part
    ): ModelResponse
}