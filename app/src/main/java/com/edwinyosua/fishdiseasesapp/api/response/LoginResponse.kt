package com.edwinyosua.fishdiseasesapp.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

//	@field:SerializedName("token")
//	val token: String? = null

)
