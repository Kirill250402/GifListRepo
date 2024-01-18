package com.voinov.giflist.data.apiService

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IGiphyApiService {

    @GET("trending")
    suspend fun getAllGifs(
        @Query(API_KEY) apiKey: String = API_KEY_VALUE,
        @Query(LIMIT_KEY) limit: Int = LIMIT_KEY_VALUE,
    ): Response<DataResponse>

    @GET("search")
    suspend fun searchGif(
        @Query(API_KEY) apiKey: String = API_KEY_VALUE,
        @Query(SEARCH_KEY) searchQuery: String = SEARCH_KEY_DEFAULT_VALUE,
    ): Response<DataResponse>

    companion object {
        private const val API_KEY_VALUE = "EEjeWKnay8eNwJ091mC2ffGuQe96tdBN"
        private const val API_KEY = "api_key"
        private const val LIMIT_KEY = "limit"
        private const val LIMIT_KEY_VALUE = 50
        private const val SEARCH_KEY = "q"
        private const val SEARCH_KEY_DEFAULT_VALUE = "hi"
    }
}


