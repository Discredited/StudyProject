package com.june.studyproject.expand.image.watcher

import android.view.MotionEvent
import com.june.imageabout.watcher.drag.OnImageDragListener
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.databinding.ActivityImageDragLayoutBinding

class ImageDragLayoutActivity : StudyBaseActivity<ActivityImageDragLayoutBinding>() {

    private val mImageDraListener: OnImageDragListener = object : OnImageDragListener {
        override fun onDragStateChange(state: Int, x: Float, y: Float) {
            val dragState = when (state) {
                MotionEvent.ACTION_DOWN -> "ACTION_DOWN"
                MotionEvent.ACTION_MOVE -> "ACTION_MOVE"
                MotionEvent.ACTION_UP -> "ACTION_UP"
                MotionEvent.ACTION_CANCEL -> "ACTION_CANCEL"
                else -> "UN KNOW"
            }
            mBinding.tvDragStatus.text = dragState
            mBinding.tvDragInfo.text = "x:${x}\ny:${y}"
        }

        override fun onDragOverThreshold() {
            onBackPressed()
        }
    }

    override fun initView() {
        mBinding.vImageDragLayout.setImageDragListener(mImageDraListener)
    }

    override fun loadData() {
    }
}