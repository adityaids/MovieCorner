package com.indeep.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indeep.core.data.source.local.entity.GenreEntity
import com.indeep.core.data.source.local.entity.MovieEntity
import com.indeep.core.data.source.local.entity.ReviewEntity
import com.indeep.core.data.source.local.entity.TrailerEntity

@Database(entities = [GenreEntity::class,
    MovieEntity::class,
    ReviewEntity::class,
    TrailerEntity::class],
    version = 1)
abstract class MovieDb: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}