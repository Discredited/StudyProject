package com.june.studyproject.expand.image.watcher

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.imageabout.watcher.drag.OnImageDragListener
import com.june.imageabout.watcher.progress.LoadingProgressListener
import com.june.imageabout.watcher.progress.interceptor.ProgressInterceptor
import com.june.studyproject.R
import com.june.studyproject.base.glide.GlideApp
import com.june.studyproject.expand.image.box.MediaVo
import kotlinx.android.synthetic.main.item_image_watch.view.*
import timber.log.Timber

class ImageWatchAdapter : BaseQuickAdapter<MediaVo, BaseViewHolder>(R.layout.item_image_watch) {

    private var mImageDragListener: OnImageDragListener? = null

    override fun convert(holder: BaseViewHolder, item: MediaVo) {
        ProgressInterceptor.addListener(item.thumbnail, object : LoadingProgressListener {
            override fun onProgress(progress: Long, total: Long, percent: Float) {
                Timber.e("${Thread.currentThread().name}    progress:$progress    total:$total    percent:$percent")
                holder.itemView.vProgress.setProgress(percent)
            }
        })

        GlideApp.with(holder.itemView.context)
            .load(item.thumbnail)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.itemView.vImageView)

        mImageDragListener?.let {
            holder.itemView.vImageDragLayout.setImageDragListener(it)
        }
    }

    fun setImageDragListener(listener: OnImageDragListener) {
        mImageDragListener = listener
    }
}