package com.voinov.giflist.ui.recyclerUtility

import androidx.recyclerview.widget.DiffUtil
import com.voinov.giflist.data.apiService.GiphyModelApiResponse

class GifDiffUtil(
    private val oldList: ArrayList<GiphyModelApiResponse>,
    private val newList: List<GiphyModelApiResponse>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition].id == oldList[oldItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition].images.downsized.url == oldList[oldItemPosition].images.downsized.url

}