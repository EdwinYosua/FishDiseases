package com.edwinyosua.fishdiseasesapp.data.network.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("user")
    val user: String?,

    @field:SerializedName("error")
    val error: Boolean?,

//    @field:SerializedName("login")
//    val login: LoginResponse

)
