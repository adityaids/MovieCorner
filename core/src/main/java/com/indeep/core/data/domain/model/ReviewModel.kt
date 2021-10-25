package com.indeep.core.data.domain.model

data class ReviewModel(
    var id: Int? = 0,
    val movieId: Int,
    val author: String,
    val content: String,
    val avatarPath: String,
    val rating: Double,
    val createdAt: String
)