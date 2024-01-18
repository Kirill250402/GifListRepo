package com.voinov.giflist.core

import com.voinov.giflist.ui.gifListFeature.GifListViewModel
import com.voinov.giflist.ui.gifSearch.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GifListViewModel(gifListUseCase = get()) }
    viewModel { SearchViewModel(searchGifUseCase = get()) }
}