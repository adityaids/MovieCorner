package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreListResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("genres")
    val listGenre: List<GenreResponse>
)