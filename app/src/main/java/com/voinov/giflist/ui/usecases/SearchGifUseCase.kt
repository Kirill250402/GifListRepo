package com.voinov.giflist.ui.usecases

import com.voinov.giflist.data.repository.IGifListRepository

class SearchGifUseCase(
    private val iGifListRepository: IGifListRepository
) {
    suspend operator fun invoke(searchQuery: String) = iGifListRepository.searchGif(searchQuery)
}