package com.edwinyosua.fishdiseasesapp.utils

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import java.io.File

object Gallery {

    var currentImgUri: Uri? = null

    lateinit var imgFile: File
    var launcherGallery: ActivityResultLauncher<PickVisualMediaRequest>? = null

}