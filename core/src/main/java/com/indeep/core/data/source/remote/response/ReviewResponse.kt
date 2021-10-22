package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(

	@field:SerializedName("id")
	val movieId: Int,

	@field:SerializedName("results")
	val results: List<ReviewItemResponse>
)