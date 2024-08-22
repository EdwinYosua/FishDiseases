package com.edwinyosua.fishdiseasesapp.utils.ext

import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder


fun Fragment.dialogFragment(
    title: String,
    msg: String = "",
    positiveBttnTxt: String,
    negativeBttnTxt: String,
    onPositiveClick: () -> Unit
) {
    MaterialAlertDialogBuilder(requireContext()).apply {
        setTitle(title)
        setNegativeButton(negativeBttnTxt) { action, _ ->
            action.cancel()
        }

        if (msg != "") {
            setMessage(msg)
        }

        setPositiveButton(positiveBttnTxt) { _, _ ->
            onPositiveClick()
        }
    }.create().show()

}