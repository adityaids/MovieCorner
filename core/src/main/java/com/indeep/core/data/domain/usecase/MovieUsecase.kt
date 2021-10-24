package com.indeep.core.data.domain.usecase

import androidx.paging.PagedList
import com.indeep.core.data.domain.model.GenreListModel
import com.indeep.core.data.domain.model.MovieModel
import com.indeep.core.data.domain.model.ReviewModel
import com.indeep.core.data.domain.model.TrailerModel
import com.indeep.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUsecase {
    fun getAllMovie(): Flow<Resource<PagedList<MovieModel>>>
    fun getMovieByGenre(genreId: Int): Flow<Resource<PagedList<MovieModel>>>
    fun getTrailerById(movieId: Int): Flow<Resource<TrailerModel>>
    fun getAllGenre(): Flow<Resource<List<GenreListModel>>>
    fun getDetailMovie(movieId: Int): Flow<Resource<MovieModel>>
    fun getMovieReview(movieId: Int): Flow<Resource<PagedList<ReviewModel>>>
}