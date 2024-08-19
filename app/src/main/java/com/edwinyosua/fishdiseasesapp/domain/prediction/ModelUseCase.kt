package com.edwinyosua.fishdiseasesapp.domain.prediction

import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.domain.prediction.entities.Prediction
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ModelUseCase {

    fun predict(imgUri: File): Flow<ApiResult<Prediction>>

}