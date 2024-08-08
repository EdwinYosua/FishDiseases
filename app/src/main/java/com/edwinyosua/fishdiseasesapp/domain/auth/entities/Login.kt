package com.edwinyosua.fishdiseasesapp.domain.auth.entities

import com.edwinyosua.fishdiseasesapp.utils.ext.emptyString

data class Login(
    val name: String = emptyString(),
    val message: String = emptyString(),
    val userId: String = emptyString(),
    val error: Boolean,
)