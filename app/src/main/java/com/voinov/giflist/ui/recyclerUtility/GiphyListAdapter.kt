package com.voinov.giflist.ui.recyclerUtility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.voinov.giflist.R
import com.voinov.giflist.data.apiService.GiphyModelApiResponse


class GiphyListAdapter : RecyclerView.Adapter<GiphyListAdapter.GiphyListViewHolder>() {

    private val gifList = ArrayList<GiphyModelApiResponse>()

    override fun onBindViewHolder(holder: GiphyListViewHolder, position: Int) {
        holder.bindItem(gifList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.giphy_list_item_layout, parent, false)

        return GiphyListViewHolder(view)
    }

    override fun getItemCount(): Int = gifList.size

    class GiphyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView
        private val image: ImageView

        init {
            title = itemView.findViewById(R.id.gifTitle)
            image = itemView.findViewById(R.id.gifImg)
        }

        fun bindItem(item: GiphyModelApiResponse) {
            title.text = item.title
            Glide.with(itemView.context)
                .asGif()
                .load(item.images.downsized.url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(image)
        }
    }

    fun setList(newList: List<GiphyModelApiResponse>) {
        val diffUtilCallBack = GifDiffUtil(this.gifList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)

        gifList.clear()
        gifList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}