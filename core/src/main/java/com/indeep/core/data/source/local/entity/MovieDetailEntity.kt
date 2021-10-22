package com.indeep.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieDetailEntity(
    @Embedded
    val movie: MovieEntity,

    @Relation(parentColumn = "movie_id", entityColumn = "movie_id")
    val listGenre: List<GenreEntity>,

    @Relation(parentColumn = "movie_id", entityColumn = "movie_id")
    val listReview: List<ReviewEntity>
)