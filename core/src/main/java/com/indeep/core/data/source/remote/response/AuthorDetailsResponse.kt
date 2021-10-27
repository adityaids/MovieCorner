package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AuthorDetailsResponse(

	@field:SerializedName("avatar_path")
	val avatarPath: String,

	@field:SerializedName("rating")
	val rating: Double
)