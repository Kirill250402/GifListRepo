package com.voinov.giflist.data.repository

import com.voinov.giflist.data.ApiResult
import com.voinov.giflist.data.apiService.DataResponse
import com.voinov.giflist.data.sources.GifListRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class IGifListRepositoryImpl(
    private val gifListRemoteDataSource: GifListRemoteDataSource,
    private val dispatcher: CoroutineDispatcher,
) : IGifListRepository {

    override suspend fun getGifList(): ApiResult<DataResponse> = withContext(dispatcher) {
        gifListRemoteDataSource.fetchGifList()
    }

    override suspend fun searchGif(searchQuery: String): ApiResult<DataResponse> = withContext(dispatcher) {
        gifListRemoteDataSource.searchGif(searchQuery)
    }
}