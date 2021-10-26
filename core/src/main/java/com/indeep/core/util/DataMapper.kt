package com.indeep.core.util

import com.indeep.core.data.domain.model.*
import com.indeep.core.data.source.local.entity.*
import com.indeep.core.data.source.remote.response.*

object DataMapper {
    fun mapListMovieDetailResponseToEntities(input: MovieListResponse): List<MovieDetailEntity>{
        val moveList = ArrayList<MovieDetailEntity>()
        val genreList = ArrayList<GenreEntity>()
        for (movieResponse in input.results){
            val movieEntity = MovieEntity(
                movieResponse.movieId,
                movieResponse.title,
                movieResponse.description,
                movieResponse.video,
                movieResponse.posterPath,
                movieResponse.backdropPath,
                movieResponse.releaseDate,
                movieResponse.voteAverage
            )
            for (genre in movieResponse.genreIds){
                val mGenre = GenreEntity(
                    genre,
                    movieResponse.movieId
                )
                genreList.add(mGenre)
            }
            val movieDetail = MovieDetailEntity(
                movieEntity,
                genreList
                )
            moveList.add(movieDetail)
        }
        return moveList
    }

    fun mapListMovieDetailDomainToEntities(input: List<MovieDetailModel>): List<MovieDetailEntity> {
        val movieList = ArrayList<MovieDetailEntity>()
        input.map {
            val movie = MovieDetailEntity(
                movie = mapMovieDomainToEntity(it.movie),
                listGenre = mapGenreMovieToDomain(it.listGenre)
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieDetailEntityToDomain(input: MovieDetailEntity): MovieDetailModel{
        return MovieDetailModel(
            mapMovieEntitiyToDomain(input.movie),
            mapGenreMovieEntitiesToDomain(input.listGenre)
        )
    }

    fun mapTrailerEntitiesToDomain(input: List<TrailerEntity>): List<TrailerModel>{
        val trailerList = ArrayList<TrailerModel>()
        input.map {
            val trailer = TrailerModel(
                id = it.id,
                movieId = it.movieId,
                site = it.site,
                key = it.key
            )
            trailerList.add(trailer)
        }
        return trailerList
    }

    fun mapTrailerResponseToEntities(input: TrailerListResponse): List<TrailerEntity>{
        val trailerList = ArrayList<TrailerEntity>()
        input.results.map {
            val trailer = TrailerEntity(
                id = it.id,
                movieId = input.id,
                site = it.site,
                key = it.key
            )
            trailerList.add(trailer)
        }
        return trailerList
    }

    fun mapAllGenreEntitiesToDomain(input: List<GenreListEntity>): List<GenreListModel>{
        val genreList = ArrayList<GenreListModel>()
        input.map {
            val genre = GenreListModel(
                id = it.id,
                name = it.name
            )
            genreList.add(genre)
        }
        return genreList
    }

    fun mapAllGenreResponseToEntities(input: GenreListResponse): List<GenreListEntity>{
        val genreList = ArrayList<GenreListEntity>()
        input.listGenre.map {
            val genre = GenreListEntity(
                id = it.id,
                name = it.name
            )
            genreList.add(genre)
        }
        return genreList
    }

    fun mapReviewResponseToEntity(input: ReviewResponse): List<ReviewEntity>{
        val reviewList = ArrayList<ReviewEntity>()
        input.results.map {
            val review = ReviewEntity(
                id = it.id,
                movieId = input.movieId,
                author = it.author,
                content = it.content,
                avatarPath = it.authorDetailsResponse.avatarPath,
                rating = it.authorDetailsResponse.rating,
                createdAt = it.createdAt
            )
            reviewList.add(review)
        }
        return reviewList
    }

    fun mapReviewEntityToDomain(input: ReviewEntity): ReviewModel =
        ReviewModel(
            input.id,
            input.movieId,
            input.author,
            input.content,
            input.avatarPath,
            input.rating,
            input.createdAt
        )

    fun mapGenreEntitiesToDomain(input: GenreListEntity): GenreListModel = GenreListModel(
        id = input.id,
        name = input.name
    )

    fun mapMovieEntitiyToDomain(input: MovieEntity): MovieModel{
        return MovieModel(
            input.movieId,
            input.title,
            input.description,
            input.video,
            input.posterPath,
            input.backdropPath,
            input.releaseDate,
            input.voteAverage
        )
    }

    fun mapGenreMovieEntitiesToDomain(input: List<GenreEntity>): List<GenreModel>{
        val listGenre = ArrayList<GenreModel>()
        input.map {
            val genre = GenreModel(
                id = it.id,
                movieId = it.movieId
            )
            listGenre.add(genre)
        }
        return listGenre
    }

    fun mapMovieDomainToEntity(input: MovieModel): MovieEntity{
        return MovieEntity(
            input.movieId,
            input.title,
            input.description,
            input.video,
            input.posterPath,
            input.backdropPath,
            input.releaseDate,
            input.voteAverage
        )
    }

    fun mapGenreMovieToDomain(input: List<GenreModel>): List<GenreEntity>{
        val listGenre = ArrayList<GenreEntity>()
        input.map {
            val genre = GenreEntity(
                id = it.id,
                movieId = it.movieId
            )
            listGenre.add(genre)
        }
        return listGenre
    }
}