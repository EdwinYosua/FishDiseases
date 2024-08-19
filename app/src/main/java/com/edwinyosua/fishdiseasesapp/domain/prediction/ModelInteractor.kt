package com.edwinyosua.fishdiseasesapp.domain.prediction

import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.domain.prediction.entities.Prediction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class ModelInteractor(private val modelRepo: IModelRepository) : ModelUseCase {

    override fun predict(imgUri: File): Flow<ApiResult<Prediction>> {
        return modelRepo.predict(imgUri).flowOn(Dispatchers.IO)
    }


}