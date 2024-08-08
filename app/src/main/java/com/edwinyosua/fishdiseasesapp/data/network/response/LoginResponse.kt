package com.edwinyosua.fishdiseasesapp.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("error")
    val error: Boolean,

//	@field:SerializedName("token")
//	val token: String? = null
)


data class ErrorResponse(
    val error: Boolean,
    val message: String
)
