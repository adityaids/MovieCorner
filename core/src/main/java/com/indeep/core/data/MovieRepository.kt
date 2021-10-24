package com.indeep.core.data

import androidx.paging.PagedList
import com.indeep.core.data.domain.model.GenreListModel
import com.indeep.core.data.domain.model.MovieModel
import com.indeep.core.data.domain.model.ReviewModel
import com.indeep.core.data.domain.model.TrailerModel
import com.indeep.core.data.domain.repository.IMovieRepository
import com.indeep.core.data.source.NetworkBoundSource
import com.indeep.core.data.source.Resource
import com.indeep.core.data.source.local.LocalDataSource
import com.indeep.core.data.source.local.entity.MovieEntity
import com.indeep.core.data.source.remote.RemoteDataSource
import com.indeep.core.data.source.remote.network.ApiResponse
import com.indeep.core.data.source.remote.response.MovieListResponse
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
):IMovieRepository {
    override fun getAllMovie(): Flow<Resource<PagedList<MovieModel>>> =
        object : NetworkBoundSource<PagedList<MovieModel>, PagedList<MovieListResponse>>(){
            override fun loadFromDB(): Flow<PagedList<MovieModel>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: PagedList<MovieModel>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<PagedList<MovieListResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: PagedList<MovieListResponse>) {
                TODO("Not yet implemented")
            }

        }.asFlow()

    override fun getMovieByGenre(genreId: Int): Flow<Resource<PagedList<MovieModel>>> {
        TODO("Not yet implemented")
    }

    override fun getTrailerById(movieId: Int): Flow<Resource<TrailerModel>> {
        TODO("Not yet implemented")
    }

    override fun getAllGenre(): Flow<Resource<List<GenreListModel>>> {
        TODO("Not yet implemented")
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<MovieModel>> {
        TODO("Not yet implemented")
    }

    override fun getMovieReview(movieId: Int): Flow<Resource<PagedList<ReviewModel>>> {
        TODO("Not yet implemented")
    }

}