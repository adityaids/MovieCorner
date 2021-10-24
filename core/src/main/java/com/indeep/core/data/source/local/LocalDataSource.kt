package com.indeep.core.data.source.local

import androidx.paging.DataSource
import com.indeep.core.data.source.local.entity.*
import com.indeep.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getAllMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getAllMovie()
    fun getMovieByGenre(genreId: Int): DataSource.Factory<Int, MovieEntity> = movieDao.getMovieByGenre(genreId)
    fun getTrailerById(id: Int): Flow<TrailerEntity> = movieDao.getTrailerById(id)
    fun getAllGenre(): Flow<GenreListEntity> = movieDao.getAllGenre()
    fun getGetDetailMovie(movieId: Int): Flow<MovieDetailEntity> = movieDao.getDetailMovie(movieId)
    fun getMovieReview(movieId: Int): DataSource.Factory<Int, ReviewEntity> = movieDao.getMovieReview(movieId)
    suspend fun insertMovie(movieEntity: MovieEntity) = movieDao.insertMovie(movieEntity)
    suspend fun insertAllGenre(genreListEntity: GenreListEntity) = movieDao.insertAllGenre(genreListEntity)
    suspend fun insertGenreForMovie(genreEntity: GenreEntity) = movieDao.insertGenreForMovie(genreEntity)
    suspend fun insertReview(reviewEntity: ReviewEntity) = movieDao.insertReview(reviewEntity)
    suspend fun insertTrailer(trailerEntity: TrailerEntity) = movieDao.insertTrailer(trailerEntity)
}