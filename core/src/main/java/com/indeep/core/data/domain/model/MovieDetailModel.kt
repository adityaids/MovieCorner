package com.indeep.core.data.domain.model

import android.os.Parcelable
import com.indeep.core.data.source.local.entity.GenreEntity
import com.indeep.core.data.source.local.entity.MovieEntity
import com.indeep.core.data.source.local.entity.ReviewEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailModel(
    val movie: MovieModel,

    val listGenre: List<GenreModel>
): Parcelable