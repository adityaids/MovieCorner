package com.indeep.core.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailModel(
    val movie: MovieModel,

    val listGenre: List<GenreModel>
): Parcelable