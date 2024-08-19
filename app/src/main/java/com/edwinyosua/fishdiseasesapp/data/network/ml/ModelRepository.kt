package com.edwinyosua.fishdiseasesapp.data.network.ml

import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.di.modules.networkModule
import com.edwinyosua.fishdiseasesapp.domain.prediction.IModelRepository
import com.edwinyosua.fishdiseasesapp.domain.prediction.entities.Prediction
import com.edwinyosua.fishdiseasesapp.domain.prediction.mapper.toDomain
import com.edwinyosua.fishdiseasesapp.utils.reduceFileImg
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import java.io.File

class ModelRepository(
    private val modelServices: ModelServices,
) : IModelRepository {

    override fun predict(imgUri: File): Flow<ApiResult<Prediction>> = flow {
        try {
            emit(ApiResult.Loading)

            val img = imgUri.reduceFileImg()
            val reqImg = img.asRequestBody("image/*".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData("file", img.name, reqImg)
            val response = modelServices.predict(multipartBody)

            reloadKoin()
            if (response.prediction != null) {
                val modelResponse = response.prediction.toDomain()
                emit(ApiResult.Success(modelResponse))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message.toString()))
        }
    }


    private fun reloadKoin() {
        unloadKoinModules(networkModule)
        loadKoinModules(networkModule)
    }

}