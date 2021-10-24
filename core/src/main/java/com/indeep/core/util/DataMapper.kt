package com.indeep.core.util

import com.indeep.core.data.domain.model.GenreListModel
import com.indeep.core.data.domain.model.MovieDetailModel
import com.indeep.core.data.domain.model.MovieModel
import com.indeep.core.data.domain.model.TrailerModel
import com.indeep.core.data.source.local.entity.GenreListEntity
import com.indeep.core.data.source.local.entity.MovieDetailEntity
import com.indeep.core.data.source.local.entity.MovieEntity
import com.indeep.core.data.source.local.entity.TrailerEntity
import com.indeep.core.data.source.remote.response.GenreListResponse
import com.indeep.core.data.source.remote.response.MovieListResponse
import com.indeep.core.data.source.remote.response.TrailerListResponse

object DataMapper {
    fun mapListMovieDetailResponseToEntities(input: MovieListResponse): List<MovieDetailEntity>{
        TODO("Not yet implemented")
    }

    fun mapListMovieDetailDomainToEntities(input: List<MovieDetailModel>): List<MovieDetailEntity>{
        TODO("Not yet implemented")
    }

    fun mapMovieDetailEntityToDomain(input: MovieDetailEntity): MovieDetailModel{
        TODO("Not yet implemented")
    }

    fun mapMovieDetailDomainToEntity(input: MovieDetailModel): MovieDetailEntity{
        TODO("Not yet implemented")
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<MovieModel>{
        TODO("Not yet implemented")
    }

    fun mapMovieDomainToEntities(input: List<MovieModel>): List<MovieEntity>{
        TODO("Not yet implemented")
    }

    fun mapTrailerEntitiesToDomain(input: List<TrailerEntity>): List<TrailerModel>{
        TODO("Not yet implemented")
    }

    fun mapTrailerDomainToEntities(input: TrailerListResponse): List<TrailerEntity>{
        TODO("Not yet implemented")
    }

    fun mapAllGenreEntitiesToDomain(input: List<GenreListEntity>): List<GenreListModel>{
        TODO("Not yet implemented")
    }

    fun mapAllGenreResponseToEntities(input: GenreListResponse): List<GenreListEntity>{
        TODO("Not yet implemented")
    }

}