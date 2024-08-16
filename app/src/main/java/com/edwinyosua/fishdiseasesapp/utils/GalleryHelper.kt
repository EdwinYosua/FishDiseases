package com.edwinyosua.fishdiseasesapp.utils

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest

object Gallery {

    lateinit var currentImgUri: Uri

    lateinit var launcherGallery: ActivityResultLauncher<PickVisualMediaRequest>

}