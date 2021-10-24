package com.indeep.core.data.domain.model

data class ReviewModel(
    val id: String,
    val movieId: Int,
    val author: String,
    val content: String,
    val avatarPath: String,
    val rating: Double,
    val createdAt: String
)