package com.voinov.giflist.data.apiService

data class GiphyModelApiResponse(
    val type: String,
    val id: String,
    val title: String,
    val images: ImageModelApiResponse
)

data class ImageModelApiResponse(
    val original: OriginalGif,
    val downsized: DownsizedGif,
)

data class DownsizedGif(
    val url: String,
)

data class OriginalGif(
    val url: String,
)

data class DataResponse(
    val data: List<GiphyModelApiResponse>
)