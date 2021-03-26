package com.june.studyproject.expand.image.watcher

import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.imageabout.watcher.drag.OnImageDragListener
import com.june.imageabout.watcher.progress.LoadingProgressListener
import com.june.imageabout.watcher.progress.interceptor.ProgressInterceptor
import com.june.studyproject.R
import com.june.studyproject.base.glide.GlideApp
import com.june.studyproject.databinding.ItemImageWatchBinding
import com.june.studyproject.expand.image.box.MediaVo
import timber.log.Timber

class ImageWatchAdapter : BaseQuickAdapter<MediaVo, BaseDataBindingHolder<ItemImageWatchBinding>>(R.layout.item_image_watch) {

    private var mImageDragListener: OnImageDragListener? = null

    fun setImageDragListener(listener: OnImageDragListener) {
        mImageDragListener = listener
    }

    override fun convert(holder: BaseDataBindingHolder<ItemImageWatchBinding>, item: MediaVo) {
        ProgressInterceptor.addListener(item.thumbnail, object : LoadingProgressListener {
            override fun onProgress(progress: Long, total: Long, percent: Float) {
                Timber.e("${Thread.currentThread().name}    progress:$progress    total:$total    percent:$percent")
                holder.itemView.post {
                    holder.dataBinding?.vProgress?.setProgress(percent)
                    if (percent == 1F) {
                        ProgressInterceptor.removeListener(item.thumbnail)
                        holder.dataBinding?.vProgress?.visibility = View.GONE
                    }
                }
            }
        })

        holder.dataBinding?.vImageView?.let {
            GlideApp.with(holder.itemView.context)
                    .load(item.thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(it)
        }

        mImageDragListener?.let {
            holder.dataBinding?.vImageDragLayout?.setImageDragListener(it)
        }
        holder.dataBinding?.vImageView?.let {
            holder.dataBinding?.vImageDragLayout?.bindView(it)
        }
    }
}