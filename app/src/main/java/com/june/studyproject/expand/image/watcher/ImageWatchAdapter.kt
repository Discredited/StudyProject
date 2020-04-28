package com.june.studyproject.expand.image.watcher

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.imageabout.watcher.OnImageDragListener
import com.june.studyproject.R
import com.june.studyproject.expand.image.box.MediaVo
import kotlinx.android.synthetic.main.item_image_watch.view.*

class ImageWatchAdapter : BaseQuickAdapter<MediaVo, BaseViewHolder>(R.layout.item_image_watch) {

    private var mImageDragListener: OnImageDragListener? = null

    override fun convert(helper: BaseViewHolder, item: MediaVo) {
        Glide.with(helper.itemView.context)
            .load(item.thumbnail)
            .into(helper.itemView.vImageView)

        mImageDragListener?.let {
            helper.itemView.vImageDragLayout.setImageDragListener(it)
        }
    }

    fun setImageDragListener(listener: OnImageDragListener) {
        mImageDragListener = listener
    }
}