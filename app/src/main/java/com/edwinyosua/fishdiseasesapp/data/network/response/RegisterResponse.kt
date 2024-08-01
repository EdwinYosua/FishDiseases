package com.edwinyosua.fishdiseasesapp.data.network.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("user")
    val user: String? = null
)
