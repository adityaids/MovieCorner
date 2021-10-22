package com.indeep.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithReview(
    @Embedded
    val movie: MovieEntity,

    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    val listReview: List<ReviewEntity>
)