package com.june.imageabout.watcher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.june.imageabout.R
import com.june.imageabout.vo.ImageVo
import kotlinx.android.synthetic.main.item_image_watch.view.*

class ImageWatchAdapter : RecyclerView.Adapter<ImageWatchViewHolder>() {

    private val mItemList = mutableListOf<ImageVo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageWatchViewHolder {
        return ImageWatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_watch, parent, false))
    }

    override fun getItemCount(): Int = mItemList.size

    override fun onBindViewHolder(holder: ImageWatchViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(mItemList[position].thumbnail)
            .into(holder.itemView.vImageView)
    }

    fun setImageList(list: MutableList<ImageVo>) {
        mItemList.addAll(list)
        notifyDataSetChanged()
    }
}