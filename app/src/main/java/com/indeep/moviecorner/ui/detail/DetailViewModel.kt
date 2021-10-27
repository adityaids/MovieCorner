package com.indeep.moviecorner.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.indeep.core.data.domain.usecase.MovieUsecase

class DetailViewModel(private val movieUsecase: MovieUsecase): ViewModel() {
    fun getTrailer(movieId: Int) = movieUsecase.getTrailerById(movieId).asLiveData()
    fun getReview(movieId: Int) = movieUsecase.getMovieReview(movieId).asLiveData()

}