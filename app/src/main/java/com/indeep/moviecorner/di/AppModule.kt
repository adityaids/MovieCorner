package com.indeep.moviecorner.di

import com.indeep.core.data.domain.usecase.MovieInteractor
import com.indeep.core.data.domain.usecase.MovieUsecase
import com.indeep.moviecorner.ui.detail.DetailViewModel
import com.indeep.moviecorner.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUsecase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}