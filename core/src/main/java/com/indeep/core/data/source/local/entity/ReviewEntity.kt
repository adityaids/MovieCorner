package com.indeep.core.data.source.local.entity

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_review")
class ReviewEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "content")
    @field:SerializedName("content")
    val content: String,

    @ColumnInfo(name = "avatar_path")
    val avatarPath: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "created_at")
    val createdAt: String
)