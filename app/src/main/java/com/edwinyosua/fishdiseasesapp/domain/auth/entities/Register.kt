package com.edwinyosua.fishdiseasesapp.domain.auth.entities

import com.edwinyosua.fishdiseasesapp.utils.ext.emptyString

data class Register(

    val message: String = emptyString(),
    val user: String = emptyString(),
    val error: Boolean = false
)
