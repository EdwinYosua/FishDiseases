package com.edwinyosua.fishdiseasesapp.domain.prediction.entities

import com.edwinyosua.fishdiseasesapp.utils.ext.emptyString

data class Prediction(
    val prediction: String = emptyString()
)