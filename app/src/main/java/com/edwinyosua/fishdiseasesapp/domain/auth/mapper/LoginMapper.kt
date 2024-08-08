package com.edwinyosua.fishdiseasesapp.domain.auth.mapper

import com.edwinyosua.fishdiseasesapp.data.network.response.LoginResponse
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Login
import com.edwinyosua.fishdiseasesapp.utils.ext.emptyString

fun LoginResponse.toDomain(): Login {
    return Login(
        name = this.name ?: emptyString(),
        message = this.message ?: emptyString(),
        userId = this.userId ?: emptyString(),
        error = this.error ?: false
    )
}