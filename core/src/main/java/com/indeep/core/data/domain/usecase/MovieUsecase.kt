package com.indeep.core.data.domain.usecase

import androidx.paging.PagedList
import com.indeep.core.data.domain.model.*
import com.indeep.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUsecase {
    fun getAllMovie(): Flow<Resource<PagedList<MovieDetailModel>>>
    fun getMovieByGenre(genreId: Int): Flow<Resource<PagedList<MovieModel>>>
    fun getTrailerById(movieId: Int): Flow<Resource<List<TrailerModel>>>
    fun getAllGenre(): Flow<Resource<List<GenreListModel>>>
    fun getDetailMovie(movieId: Int): Flow<Resource<List<MovieModel>>>
    fun getMovieReview(movieId: Int): Flow<Resource<PagedList<List<ReviewModel>>>>
}