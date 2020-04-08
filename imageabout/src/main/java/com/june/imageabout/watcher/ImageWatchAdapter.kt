package com.june.imageabout.watcher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.june.imageabout.R
import com.june.imageabout.box.ImageBoxLoader
import com.june.imageabout.vo.ImageVo
import kotlinx.android.synthetic.main.item_image_watch.view.*

class ImageWatchAdapter : RecyclerView.Adapter<ImageWatchViewHolder>() {

    private val mItemList = mutableListOf<ImageVo>()
    private var mImageLoader: ImageBoxLoader? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageWatchViewHolder {
        return ImageWatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_watch, parent, false))
    }

    override fun getItemCount(): Int = mItemList.size

    override fun onBindViewHolder(holder: ImageWatchViewHolder, position: Int) {
        holder.itemView.tvImagePosition.text = "第${position}张图片"
        mImageLoader?.loadImage(holder.itemView.vImageView, mItemList[position], position)
    }

    fun setImageLoader(loader: ImageBoxLoader) {
        mImageLoader = loader
    }

    fun setImageList(list: MutableList<ImageVo>) {
        list.addAll(list)
        notifyDataSetChanged()
    }
}