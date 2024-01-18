package com.voinov.giflist.data.sources

import com.voinov.giflist.data.apiService.IGiphyApiService
import com.voinov.giflist.data.handleApi

class GifListRemoteDataSource(
    val apiClient: IGiphyApiService
) {
    suspend fun fetchGifList() = handleApi { apiClient.getAllGifs() }

    suspend fun searchGif(searchQuery: String) =
        handleApi { apiClient.searchGif(searchQuery = searchQuery) }
}