package com.indeep.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.indeep.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM table_movie")
    fun getAllMovie(): DataSource.Factory<Int, MovieDetailEntity>

    @Transaction
    @Query("SELECT *"
            +" FROM table_movie CROSS JOIN table_genre ON table_movie.movie_id = table_genre.movie_id WHERE id = :genreId")
    fun getMovieByGenre(genreId: Int): DataSource.Factory<Int, MovieDetailEntity>

    @Query("SELECT * FROM table_trailer WHERE movie_id = :movieId")
    fun getTrailerById(movieId: Int): Flow<TrailerEntity>

    @Query("SELECT * FROM table_list_genre")
    fun getAllGenre(): Flow<GenreListEntity>

    @Transaction
    @Query("SELECT * FROM table_movie WHERE movie_id = :movieId")
    fun getDetailMovie(movieId: Int): Flow<MovieDetailEntity>

    @Query("SELECT * FROM table_review WHERE movie_id = :movieId")
    fun getMovieReview(movieId: Int): DataSource.Factory<Int, ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGenre(genreListEntity: List<GenreListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreForMovie(genreEntity: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(reviewEntity: List<ReviewEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrailer(trailerEntity: List<TrailerEntity>)
}