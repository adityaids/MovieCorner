package com.indeep.core.data.domain.model

data class MovieModel(
    val movieId: Int,
    val title: String,
    val description: String,
    val video: Boolean,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Float
)