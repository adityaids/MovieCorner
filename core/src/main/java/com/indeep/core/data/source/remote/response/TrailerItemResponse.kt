package com.indeep.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TrailerItemResponse(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("site")
	val site: String,

	@field:SerializedName("key")
	val key: String
)