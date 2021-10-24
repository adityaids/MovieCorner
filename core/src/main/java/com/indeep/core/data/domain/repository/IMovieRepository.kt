package com.indeep.core.data.domain.repository

import androidx.paging.PagedList
import com.indeep.core.data.domain.model.*
import com.indeep.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<PagedList<MovieDetailModel>>>
    fun getMovieByGenre(genreId: Int): Flow<Resource<PagedList<MovieDetailModel>>>
    fun getTrailerById(movieId: Int): Flow<Resource<List<TrailerModel>>>
    fun getAllGenre(): Flow<Resource<List<GenreListModel>>>
    fun getMovieReview(movieId: Int): Flow<Resource<PagedList<ReviewModel>>>
}