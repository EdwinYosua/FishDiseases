package com.edwinyosua.fishdiseasesapp.utils.ext

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

fun toastyMsg(context: Context, msg: String, status: Int) {

    Toasty.Config.getInstance()
        .allowQueue(false)
        .apply()

    when (status) {
        0 -> Toasty.error(context, msg, Toast.LENGTH_SHORT).show()
        1 -> Toasty.success(context, msg, Toast.LENGTH_SHORT).show()
        2 -> Toasty.info(context, msg, Toast.LENGTH_SHORT).show()
        3 -> Toasty.warning(context, msg, Toast.LENGTH_SHORT).show()
        else -> Toasty.normal(context, msg, Toast.LENGTH_SHORT).show()
    }
}