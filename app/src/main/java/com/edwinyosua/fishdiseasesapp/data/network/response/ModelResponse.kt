package com.edwinyosua.fishdiseasesapp.data.network.response

import com.google.gson.annotations.SerializedName

data class ModelResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    val prediction: PredictionResponse?
)