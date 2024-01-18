package com.voinov.giflist.ui.usecases

import com.voinov.giflist.data.repository.IGifListRepository

class GifListUseCase(
    private val iGifListRepository: IGifListRepository
) {
     suspend operator fun invoke() = iGifListRepository.getGifList()
}