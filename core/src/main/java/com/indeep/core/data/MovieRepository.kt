package com.indeep.core.data

import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.indeep.core.data.domain.model.*
import com.indeep.core.data.domain.repository.IMovieRepository
import com.indeep.core.data.source.NetworkBoundSource
import com.indeep.core.data.source.Resource
import com.indeep.core.data.source.local.LocalDataSource
import com.indeep.core.data.source.local.entity.MovieDetailEntity
import com.indeep.core.data.source.local.entity.MovieEntity
import com.indeep.core.data.source.remote.RemoteDataSource
import com.indeep.core.data.source.remote.network.ApiResponse
import com.indeep.core.data.source.remote.response.MovieItemResponse
import com.indeep.core.data.source.remote.response.MovieListResponse
import com.indeep.core.util.Constant
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
        object : NetworkBoundSource<PagedList<MovieDetailModel>, List<MovieItemResponse>>(){
            override fun loadFromDB(): Flow<PagedList<MovieDetailModel>> {
                val movieList = localDataSource.getAllMovie().map { DataMapper.mapMovieDetailEntityToDomain(it) }
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(movieList, config).build().asFlow()
            }

            override fun shouldFetch(data: PagedList<MovieDetailModel>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItemResponse>>> {
                return remoteDataSource.getPopularMovie(TOKEN_KEY, LANGUAGE, SORT_BY)
            }

            override suspend fun saveCallResult(data: List<MovieItemResponse>) {
                val movie: List<MovieDetailEntity> = DataMapper.mapListMovieDetailResponseToEntities(data)
                val movieList = ArrayList<MovieEntity>()
                for (dataMovie in movie) {
                    movieList.add(dataMovie.movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getMovieByGenre(genreId: Int): Flow<Resource<PagedList<MovieModel>>> {
        TODO("Not yet implemented")
    }

    override fun getTrailerById(movieId: Int): Flow<Resource<List<TrailerModel>>> {
        TODO("Not yet implemented")
    }

    override fun getAllGenre(): Flow<Resource<List<GenreListModel>>> {
        TODO("Not yet implemented")
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<List<MovieModel>>> {
        TODO("Not yet implemented")
    }

    override fun getMovieReview(movieId: Int): Flow<Resource<PagedList<List<ReviewModel>>>> {
        TODO("Not yet implemented")
    }

}