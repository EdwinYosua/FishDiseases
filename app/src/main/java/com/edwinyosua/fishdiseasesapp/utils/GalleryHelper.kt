package com.edwinyosua.fishdiseasesapp.utils

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest

object Gallery {

    var currentImgUri: Uri? = null

    lateinit var launcherGallery: ActivityResultLauncher<PickVisualMediaRequest>

}