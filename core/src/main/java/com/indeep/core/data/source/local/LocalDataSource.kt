package com.indeep.core.data.source.local

import androidx.paging.DataSource
import com.indeep.core.data.source.local.entity.*
import com.indeep.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getAllMovie(): DataSource.Factory<Int, MovieDetailEntity> = movieDao.getAllMovie()
    fun getMovieByGenre(genreId: Int): DataSource.Factory<Int, MovieDetailEntity> = movieDao.getMovieByGenre(genreId)
    fun getTrailerById(id: Int): Flow<List<TrailerEntity>> = movieDao.getTrailerById(id)
    fun getAllGenre(): Flow<List<GenreListEntity>> = movieDao.getAllGenre()
    fun getMovieReview(movieId: Int): DataSource.Factory<Int, ReviewEntity> = movieDao.getMovieReview(movieId)
    suspend fun insertMovie(movieEntity: List<MovieEntity>) = movieDao.insertMovie(movieEntity)
    suspend fun insertAllGenre(genreListEntity: List<GenreListEntity>) = movieDao.insertAllGenre(genreListEntity)
    suspend fun insertGenreForMovie(genreEntity: List<GenreEntity>) = movieDao.insertGenreForMovie(genreEntity)
    suspend fun insertReview(reviewEntity: List<ReviewEntity>) = movieDao.insertReview(reviewEntity)
    suspend fun insertTrailer(trailerEntity: List<TrailerEntity>) = movieDao.insertTrailer(trailerEntity)
}