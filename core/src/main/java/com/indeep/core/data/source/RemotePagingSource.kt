package com.indeep.core.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.indeep.core.data.source.remote.RemoteDataSource.Companion.NETWORK_PAGE_SIZE
import com.indeep.core.data.source.remote.network.ApiService
import com.indeep.core.data.source.remote.response.MovieItemResponse
import com.indeep.core.util.Constant
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE = 1
class RemotePagingSource(
    private val apiService: ApiService
): PagingSource<Int, MovieItemResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItemResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemResponse> {
        val position = params.key ?: STARTING_PAGE
        return try {
            val response = apiService.getPopularMovie(Constant.TOKEN_KEY, Constant.LANGUAGE, Constant.SORT_BY)
            val data = response.results
            val nextKey = if (data.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = data,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}