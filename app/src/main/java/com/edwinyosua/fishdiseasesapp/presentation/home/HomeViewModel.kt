package com.edwinyosua.fishdiseasesapp.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.domain.prediction.ModelUseCase
import com.edwinyosua.fishdiseasesapp.domain.prediction.entities.Prediction
import kotlinx.coroutines.launch
import java.io.File

class HomeViewModel(private val modelRepository: ModelUseCase) : ViewModel() {

    private val _modelResult = MutableLiveData<ApiResult<Prediction>?>()
    val modelResult: LiveData<ApiResult<Prediction>?> by lazy { _modelResult }


    fun analyzeImage(img: File) {
        viewModelScope.launch {
            modelRepository.predict(img).collect {
                _modelResult.value = it
                Log.d("HomeViewModel", "analyzeImg")
            }
        }
    }

    fun setToNull() {
        _modelResult.value = null
    }
}