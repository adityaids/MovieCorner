package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("id")
    val genreId: Int,
    @field:SerializedName("genre")
    val genre: String
)