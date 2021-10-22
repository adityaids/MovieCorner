package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieItemResponse(

	@field:SerializedName("id")
	val movieId: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("overview")
	val description: String,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("vote_average")
	val voteAverage: Int,

	@field:SerializedName("vote_count")
	val voteCount: Int
)