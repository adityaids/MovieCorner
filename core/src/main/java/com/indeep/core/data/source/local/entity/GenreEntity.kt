package com.indeep.core.data.source.local.entity

import androidx.room.*

@Entity(tableName = "table_genre",
    primaryKeys = ["id", "movie_id"],
    foreignKeys = [ForeignKey(entity = MovieEntity::class,
        parentColumns = ["movie_id"],
        childColumns = ["movie_id"])],
    indices = [Index(value = ["id"]),
        Index(value = ["movie_id"])]
)
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "name")
    val name: String
)