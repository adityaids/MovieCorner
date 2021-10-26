package com.indeep.core.data.domain.usecase

import androidx.paging.PagedList
import com.indeep.core.data.domain.model.*
import com.indeep.core.data.domain.repository.IMovieRepository
import com.indeep.core.data.vo.Resource
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUsecase {
    override fun getAllMovie(): Flow<Resource<PagedList<MovieDetailModel>>> =
        movieRepository.getAllMovie()

    override fun getMovieByGenre(genreId: Int): Flow<Resource<PagedList<MovieDetailModel>>> =
        movieRepository.getMovieByGenre(genreId)

    override fun getTrailerById(movieId: Int): Flow<Resource<List<TrailerModel>>> =
        movieRepository.getTrailerById(movieId)

    override fun getAllGenre(): Flow<Resource<List<GenreListModel>>> =
        movieRepository.getAllGenre()

    override fun getMovieReview(movieId: Int): Flow<Resource<PagedList<ReviewModel>>> =
        movieRepository.getMovieReview(movieId)

    override fun getGenreName(genreId: Int): GenreListModel =
        movieRepository.getGenreName(genreId)
}