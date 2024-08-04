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
        setPositiveButton(positiveBttnTxt) { _, _ ->
            onPositiveClick()
        }
        setNegativeButton(negativeBttnTxt) { action, _ ->
            action.dismiss()
        }

        if (msg != "") {
            setMessage(msg)
        }

    }.create().show()

}