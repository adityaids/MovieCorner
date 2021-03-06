package com.indeep.core.data.source.local.room

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.indeep.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM table_movie")
    fun getAllMovie(): DataSource.Factory<Int, MovieDetailEntity>

    @Transaction
    @Query("""
        SELECT * FROM table_movie INNER JOIN table_genre ON table_genre.movie_id = table_movie.movie_id WHERE table_genre.id = :genreId
    """)
    fun getMovieByGenre(genreId: Int): DataSource.Factory<Int, MovieDetailEntity>

    @Query("SELECT * FROM table_trailer WHERE movie_id = :movieId")
    fun getTrailerById(movieId: Int): Flow<List<TrailerEntity>>

    @Query("SELECT * FROM table_list_genre")
    fun getAllGenre(): Flow<List<GenreListEntity>>

    @Query("SELECT * FROM table_review WHERE movie_id = :movieId")
    fun getMovieReview(movieId: Int): DataSource.Factory<Int, ReviewEntity>

    @Query("SELECT * FROM TABLE_LIST_GENRE WHERE id = :genreId")
    fun getGenreName(genreId: Int): GenreListEntity

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