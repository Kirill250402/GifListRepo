package com.voinov.giflist.core

import com.voinov.giflist.data.repository.IGifListRepository
import com.voinov.giflist.data.repository.IGifListRepositoryImpl
import com.voinov.giflist.data.sources.GifListRemoteDataSource
import com.voinov.giflist.ui.usecases.SearchGifUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.bind
import org.koin.dsl.module

val gifSearchModule = module {
    single { SearchGifUseCase(iGifListRepository = get()) }
    single {
        IGifListRepositoryImpl(
            gifListRemoteDataSource = get(),
            dispatcher = Dispatchers.IO
        )
    } bind IGifListRepository::class
    single { GifListRemoteDataSource(apiClient = get()) }
}