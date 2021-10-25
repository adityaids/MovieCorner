package com.indeep.core.data.source.remote

import android.util.Log
import com.indeep.core.data.source.remote.network.ApiResponse
import com.indeep.core.data.source.remote.network.ApiService
import com.indeep.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    companion object{
        const val NETWORK_PAGE_SIZE = 25
    }
    suspend fun getPopularMovie(
        key: String,
        language: String,
        sort: String
    ): Flow<ApiResponse<MovieListResponse>>{
        return flow {
            try {
                val response = apiService.getPopularMovie(key, language, sort)
                if (response.results.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieByGenre(
        key: String,
        language: String,
        sort: String,
        genreId: Int
    ): Flow<ApiResponse<MovieListResponse>>{
        return flow {
            try {
                val response = apiService.getMovieByGenre(key, language, sort, genreId)
                if (response.results.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllGenre(
        key: String,
        language: String
    ):Flow<ApiResponse<GenreListResponse>>{
        return flow {
            try {
                val response = apiService.getAllGenre(key, language)
                if (response.listGenre.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieTrailer(
        movieId: Int,
        key: String,
        language: String
    ): Flow<ApiResponse<TrailerListResponse>>{
        return flow {
            try {
                val response = apiService.getMovieTrailer(movieId, key, language)
                if (response.results.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieReview(
        movieId: Int,
        key: String,
        language: String
    ): Flow<ApiResponse<ReviewResponse>>{
        return flow {
            try {
                val response = apiService.getMovieReview(movieId, key, language)
                if (response.results.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}