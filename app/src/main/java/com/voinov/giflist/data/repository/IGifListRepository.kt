package com.voinov.giflist.data.repository

import com.voinov.giflist.data.ApiResult
import com.voinov.giflist.data.apiService.DataResponse

interface IGifListRepository {
    suspend fun getGifList(): ApiResult<DataResponse>

    suspend fun searchGif(searchQuery: String): ApiResult<DataResponse>
}