package com.edwinyosua.fishdiseasesapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.edwinyosua.fishdiseasesapp.data.local.SettingPreference

class OnBoardingViewModel(private val pref: SettingPreference) : ViewModel() {


    fun checkToken() = pref.getUserId().asLiveData()


}