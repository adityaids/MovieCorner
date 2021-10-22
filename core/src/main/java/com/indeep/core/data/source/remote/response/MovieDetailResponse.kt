package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("id")
	val movieId: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("vote_count")
	val voteCount: Int,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("genres")
	val genres: List<GenreResponse>
)