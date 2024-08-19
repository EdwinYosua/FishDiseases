package com.edwinyosua.fishdiseasesapp.data.network.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("class")
    val clazz: String?
)