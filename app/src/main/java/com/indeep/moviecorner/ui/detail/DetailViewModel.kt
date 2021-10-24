package com.indeep.moviecorner.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.indeep.core.data.domain.model.GenreListModel
import com.indeep.core.data.domain.usecase.MovieUsecase

class DetailViewModel(private val movieUsecase: MovieUsecase): ViewModel() {
    fun getTrailer(movieId: Int) = movieUsecase.getTrailerById(movieId).asLiveData()
    fun getGenreMovie(listGenre: List<Int>): List<GenreListModel> {
        val genreList = ArrayList<GenreListModel>()
        for (genre in listGenre) {
            val data = movieUsecase.getGenreName(genre)
            genreList.add(data)
        }
        return genreList
    }
}