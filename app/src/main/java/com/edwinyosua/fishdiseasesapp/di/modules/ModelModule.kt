package com.edwinyosua.fishdiseasesapp.di.modules

import com.edwinyosua.fishdiseasesapp.data.network.ml.ModelRepository
import com.edwinyosua.fishdiseasesapp.domain.prediction.IModelRepository
import com.edwinyosua.fishdiseasesapp.domain.prediction.ModelInteractor
import com.edwinyosua.fishdiseasesapp.domain.prediction.ModelUseCase
import org.koin.dsl.module

val modelModule = module {

    factory<ModelUseCase> { ModelInteractor(get()) }

    single { ModelInteractor(get()) }

    single<IModelRepository> { ModelRepository(get()) }

}