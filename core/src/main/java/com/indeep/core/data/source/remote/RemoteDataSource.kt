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

    suspend fun getPopularMovie(
        key: String,
        language: String,
        sort: String
    ): Flow<ApiResponse<List<MovieItemResponse>>>{
        return flow {
            try {
                val response = apiService.getPopularMovie(key, language, sort)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
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
    ): Flow<ApiResponse<List<MovieItemResponse>>>{
        return flow {
            try {
                val response = apiService.getMovieByGenre(key, language, sort, genreId)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
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
    ):Flow<ApiResponse<List<GenreResponse>>>{
        return flow {
            try {
                val response = apiService.getAllGenre(key, language)
                val dataArray = response.listGenre
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.listGenre))
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
    ): Flow<ApiResponse<List<TrailerItemResponse>>>{
        return flow {
            try {
                val response = apiService.getMovieTrailer(movieId, key, language)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
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
    ): Flow<ApiResponse<List<ReviewItemResponse>>>{
        return flow {
            try {
                val response = apiService.getMovieReview(movieId, key, language)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
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