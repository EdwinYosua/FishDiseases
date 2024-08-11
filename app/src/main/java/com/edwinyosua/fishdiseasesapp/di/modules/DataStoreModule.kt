package com.edwinyosua.fishdiseasesapp.di.modules

import com.edwinyosua.fishdiseasesapp.data.local.SettingPreference
import org.koin.dsl.module

val dataStoreModule = module {

    single<SettingPreference> { SettingPreference(get()) }

    single { SettingPreference(get()) }
}