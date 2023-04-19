package com.june.studyproject.component.view.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import timber.log.Timber

class ViewDispatchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Timber.i("View dispatchTouchEvent 分发事件：${event.actionName()}")
        val dispatchTouchEvent = super.dispatchTouchEvent(event)
        //Timber.i("View dispatchTouchEvent  $dispatchTouchEvent 分发事件：${event.actionName()}")
        return dispatchTouchEvent
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Timber.i("View onTouchEvent 消费事件：${event.actionName()}")
        val touchEvent = super.onTouchEvent(event)
        //Timber.i("View onTouchEvent $touchEvent 消费事件：${event.actionName()}")
        return touchEvent
    }
}