package com.edwinyosua.fishdiseasesapp.utils.ext

import android.text.TextUtils
import android.util.Patterns

fun emptyString(): String = ""


fun String.isEmailValid(): Boolean =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

