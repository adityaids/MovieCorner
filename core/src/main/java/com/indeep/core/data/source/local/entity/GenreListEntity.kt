package com.indeep.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "table_list_genre")
data class GenreListEntity(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String
)