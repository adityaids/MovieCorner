package com.indeep.core.data.source.remote.network

import com.indeep.core.data.source.remote.response.GenreListResponse
import com.indeep.core.data.source.remote.response.MovieListResponse
import com.indeep.core.data.source.remote.response.ReviewResponse
import com.indeep.core.data.source.remote.response.TrailerListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getPopularMovie(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String
    ): MovieListResponse

    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("with_genre") genreId: String
    ): MovieListResponse

    @GET("genre/movie/list")
    suspend fun getAllGenre(
        @Query("api_key") key: String,
        @Query("language") language: String
    ): GenreListResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): TrailerListResponse

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReview(
        @Path("movieId") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): ReviewResponse
}