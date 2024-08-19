package com.edwinyosua.fishdiseasesapp.domain.prediction.mapper

import com.edwinyosua.fishdiseasesapp.data.network.response.PredictionResponse
import com.edwinyosua.fishdiseasesapp.domain.prediction.entities.Prediction
import com.edwinyosua.fishdiseasesapp.utils.ext.emptyString

fun PredictionResponse.toDomain(): Prediction {
    return Prediction(
        prediction = this.clazz ?: emptyString()
    )
}