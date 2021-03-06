package com.indeep.moviecorner.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.indeep.core.data.domain.usecase.MovieUsecase

class MainViewModel(private val movieUsecase: MovieUsecase): ViewModel() {
    val getPopularMovie = movieUsecase.getAllMovie().asLiveData()
    val getAllGenre = movieUsecase.getAllGenre().asLiveData()
    fun getMovieByGenre(genreId: Int) = movieUsecase.getMovieByGenre(genreId).asLiveData()
}