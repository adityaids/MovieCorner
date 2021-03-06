package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TrailerListResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("results")
	val results: List<TrailerItemResponse>
)