package com.edwinyosua.fishdiseasesapp.domain.auth.mapper

import com.edwinyosua.fishdiseasesapp.data.network.response.RegisterResponse
import com.edwinyosua.fishdiseasesapp.domain.auth.entities.Register
import com.edwinyosua.fishdiseasesapp.utils.ext.emptyString

fun RegisterResponse.toDomain(): Register {

    return Register(
        message = this.message ?: emptyString(),
        user = this.user ?: emptyString(),
        error = this.error ?: false
    )

}