package com.voinov.giflist.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.voinov.giflist.BuildConfig
import com.voinov.giflist.data.apiService.IGiphyApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 15L

val networkModule = module {

    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

    single<Gson> { GsonBuilder().create() }

    val giphyRetrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GIF_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    single { giphyRetrofit.create(IGiphyApiService::class.java) }

}