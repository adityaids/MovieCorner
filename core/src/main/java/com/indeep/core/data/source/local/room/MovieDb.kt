package com.indeep.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indeep.core.data.source.local.entity.*

@Database(entities = [GenreEntity::class,
    MovieEntity::class,
    ReviewEntity::class,
    GenreListEntity::class,
    TrailerEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieDb: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}