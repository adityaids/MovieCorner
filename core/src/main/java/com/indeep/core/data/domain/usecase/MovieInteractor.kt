package com.indeep.core.data.domain.usecase

import androidx.paging.PagedList
import com.indeep.core.data.domain.model.GenreListModel
import com.indeep.core.data.domain.model.MovieModel
import com.indeep.core.data.domain.model.ReviewModel
import com.indeep.core.data.domain.model.TrailerModel
import com.indeep.core.data.domain.repository.IMovieRepository
import com.indeep.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUsecase {
    override fun getAllMovie(): Flow<Resource<PagedList<MovieModel>>> =
        movieRepository.getAllMovie()

    override fun getMovieByGenre(genreId: Int): Flow<Resource<PagedList<MovieModel>>> =
        movieRepository.getMovieByGenre(genreId)

    override fun getTrailerById(movieId: Int): Flow<Resource<TrailerModel>> =
        movieRepository.getTrailerById(movieId)

    override fun getAllGenre(): Flow<Resource<List<GenreListModel>>> =
        movieRepository.getAllGenre()

    override fun getDetailMovie(movieId: Int): Flow<Resource<MovieModel>> =
        movieRepository.getDetailMovie(movieId)

    override fun getMovieReview(movieId: Int): Flow<Resource<PagedList<ReviewModel>>> =
        movieRepository.getMovieReview(movieId)
}