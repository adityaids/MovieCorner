package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewItemResponse(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("author_details")
	val authorDetailsResponse: AuthorDetailsResponse,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("content")
	val content: String,
)