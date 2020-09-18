package com.june.studyproject.expand.image.watcher

import android.view.MotionEvent
import com.june.imageabout.watcher.drag.OnImageDragListener
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import kotlinx.android.synthetic.main.activity_image_drag_layout.*

class ImageDragLayoutActivity : BaseActivity() {

    private val mImageDraListener: OnImageDragListener = object : OnImageDragListener {
        override fun onDragStateChange(state: Int, x: Float, y: Float) {
            val dragState = when (state) {
                MotionEvent.ACTION_DOWN -> "ACTION_DOWN"
                MotionEvent.ACTION_MOVE -> "ACTION_MOVE"
                MotionEvent.ACTION_UP -> "ACTION_UP"
                MotionEvent.ACTION_CANCEL -> "ACTION_CANCEL"
                else -> "UN KNOW"
            }
            tvDragStatus.text = dragState
            tvDragInfo.text = "x:${x}\ny:${y}"
        }

        override fun onDragOverThreshold() {
            onBackPressed()
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_image_drag_layout

    override fun initView() {
        vImageDragLayout.setImageDragListener(mImageDraListener)
    }

    override fun loadData() {
    }
}