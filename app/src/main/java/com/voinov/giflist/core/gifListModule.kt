package com.voinov.giflist.core

import com.voinov.giflist.ui.usecases.GifListUseCase
import org.koin.dsl.module

val gifListModule = module {
    single { GifListUseCase(iGifListRepository = get()) }
}