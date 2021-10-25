package com.indeep.core.data

import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.indeep.core.data.domain.model.*
import com.indeep.core.data.domain.repository.IMovieRepository
import com.indeep.core.data.source.NetworkBoundSource
import com.indeep.core.data.source.Resource
import com.indeep.core.data.source.local.LocalDataSource
import com.indeep.core.data.source.local.entity.GenreEntity
import com.indeep.core.data.source.local.entity.MovieDetailEntity
import com.indeep.core.data.source.local.entity.MovieEntity
import com.indeep.core.data.source.remote.RemoteDataSource
import com.indeep.core.data.source.remote.network.ApiResponse
import com.indeep.core.data.source.remote.response.*
import com.indeep.core.util.Constant.Companion.LANGUAGE
import com.indeep.core.util.Constant.Companion.SORT_BY
import com.indeep.core.util.Constant.Companion.TOKEN_KEY
import com.indeep.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
):IMovieRepository {

    override fun getAllMovie(): Flow<Resource<PagedList<MovieDetailModel>>> =
        object : NetworkBoundSource<PagedList<MovieDetailModel>, MovieListResponse>(){
            override fun loadFromDB(): Flow<PagedList<MovieDetailModel>> {
                val movieList = localDataSource.getAllMovie().map { DataMapper.mapMovieDetailEntityToDomain(it) }
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(500)
                    .build()
                return LivePagedListBuilder(movieList, config).build().asFlow()
            }

            override fun shouldFetch(data: PagedList<MovieDetailModel>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<MovieListResponse>> {
                return remoteDataSource.getPopularMovie(TOKEN_KEY, LANGUAGE, SORT_BY)
            }

            override suspend fun saveCallResult(data: MovieListResponse) {
                val movieList: List<MovieDetailEntity> = DataMapper.mapListMovieDetailResponseToEntities(data)
                val mMovie = ArrayList<MovieEntity>()
                val genreList = ArrayList<GenreEntity>()
                for (dataMovie in movieList) {
                    mMovie.add(dataMovie.movie)
                    for (genre in dataMovie.listGenre) {
                        genreList.addAll(dataMovie.listGenre)
                    }
                }
                localDataSource.insertGenreForMovie(genreList)
                localDataSource.insertMovie(mMovie)
            }
        }.asFlow()

    override fun getMovieByGenre(genreId: Int): Flow<Resource<PagedList<MovieDetailModel>>> =
        object : NetworkBoundSource<PagedList<MovieDetailModel>, MovieListResponse>(){
            override fun loadFromDB(): Flow<PagedList<MovieDetailModel>> {
                val movieList = localDataSource.getAllMovie().map { DataMapper.mapMovieDetailEntityToDomain(it) }
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(movieList, config).build().asFlow()
            }

            override fun shouldFetch(data: PagedList<MovieDetailModel>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<MovieListResponse>> =
                remoteDataSource.getMovieByGenre(TOKEN_KEY, LANGUAGE, SORT_BY, genreId)


            override suspend fun saveCallResult(data: MovieListResponse) {
                val movieList: List<MovieDetailEntity> = DataMapper.mapListMovieDetailResponseToEntities(data)
                val mMovie = ArrayList<MovieEntity>()
                for (dataMovie in movieList) {
                    mMovie.add(dataMovie.movie)
                    localDataSource.insertGenreForMovie(dataMovie.listGenre)
                }
                localDataSource.insertMovie(mMovie)
            }

        }.asFlow()

    override fun getTrailerById(movieId: Int): Flow<Resource<List<TrailerModel>>> =
        object : NetworkBoundSource<List<TrailerModel>, TrailerListResponse>(){
            override fun loadFromDB(): Flow<List<TrailerModel>> =
                localDataSource.getTrailerById(movieId).map { DataMapper.mapTrailerEntitiesToDomain(it) }

            override fun shouldFetch(data: List<TrailerModel>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<TrailerListResponse>> =
                remoteDataSource.getMovieTrailer(movieId, TOKEN_KEY, LANGUAGE)

            override suspend fun saveCallResult(data: TrailerListResponse) =
                localDataSource.insertTrailer(DataMapper.mapTrailerResponseToEntities(data))


        }.asFlow()

    override fun getAllGenre(): Flow<Resource<List<GenreListModel>>> =
        object : NetworkBoundSource<List<GenreListModel>, GenreListResponse>(){
            override fun loadFromDB(): Flow<List<GenreListModel>> =
                localDataSource.getAllGenre().map { DataMapper.mapAllGenreEntitiesToDomain(it) }

            override fun shouldFetch(data: List<GenreListModel>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GenreListResponse>> =
                remoteDataSource.getAllGenre(TOKEN_KEY, LANGUAGE)


            override suspend fun saveCallResult(data: GenreListResponse) {
                localDataSource.insertAllGenre(DataMapper.mapAllGenreResponseToEntities(data))
            }

        }.asFlow()

    override fun getMovieReview(movieId: Int): Flow<Resource<PagedList<ReviewModel>>> =
        object : NetworkBoundSource<PagedList<ReviewModel>, ReviewResponse>(){
            override fun loadFromDB(): Flow<PagedList<ReviewModel>> {
                val reviewList = localDataSource.getMovieReview(movieId)
                    .map{ DataMapper.mapReviewEntityToDomain(it) }
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(reviewList, config).build().asFlow()
            }

            override fun shouldFetch(data: PagedList<ReviewModel>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<ReviewResponse>> =
                remoteDataSource.getMovieReview(movieId, TOKEN_KEY, LANGUAGE)

            override suspend fun saveCallResult(data: ReviewResponse) {
                localDataSource.insertReview(DataMapper.mapReviewResponseToEntity(data))
            }

        }.asFlow()

    override fun getGenreName(genreId: Int): GenreListModel =
        DataMapper.mapGenreEntitiesToDomain(localDataSource.getGenreName(genreId))

}