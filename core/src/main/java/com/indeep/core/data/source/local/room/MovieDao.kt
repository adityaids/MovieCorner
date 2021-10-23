package com.indeep.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.indeep.core.data.source.local.entity.MovieEntity
import java.util.concurrent.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM table_movie")
    suspend fun getAllMovie(): DataSource.Factory<Int, MovieEntity>
}