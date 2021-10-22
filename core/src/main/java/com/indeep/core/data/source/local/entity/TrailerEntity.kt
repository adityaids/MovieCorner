package com.indeep.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_trailer")
data class TrailerEntity(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "site")
    val site: String,
    @ColumnInfo(name = "key")
    val key: String
)