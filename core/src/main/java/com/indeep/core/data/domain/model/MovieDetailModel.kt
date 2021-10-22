package com.indeep.core.data.domain.model

import com.indeep.core.data.source.local.entity.GenreEntity
import com.indeep.core.data.source.local.entity.MovieEntity
import com.indeep.core.data.source.local.entity.ReviewEntity

data class MovieDetailModel(
    val movie: MovieEntity,

    val listGenre: List<GenreEntity>,

    val listReview: List<ReviewEntity>
)