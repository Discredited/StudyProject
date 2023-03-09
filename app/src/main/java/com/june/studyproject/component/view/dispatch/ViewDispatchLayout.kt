package com.june.studyproject.component.view.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import timber.log.Timber

class ViewDispatchLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Timber.i("ViewGroup dispatchTouchEvent 分发事件：${event.actionName()}")
         val dispatchTouchEvent = super.dispatchTouchEvent(event)
        //Timber.i("ViewGroup dispatchTouchEvent  $dispatchTouchEvent 分发事件：${event.actionName()}")
        return false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        Timber.i("ViewGroup onInterceptTouchEvent 拦截事件：${event.actionName()}")
        val interceptTouchEvent = super.onInterceptTouchEvent(event)
        //Timber.i("ViewGroup onInterceptTouchEvent  $interceptTouchEvent 拦截事件：${event.actionName()}")
        return interceptTouchEvent
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Timber.i("ViewGroup onTouchEvent 消费事件：${event.actionName()}")
        val touchEvent = super.onTouchEvent(event)
        //Timber.i("ViewGroup onTouchEvent $touchEvent 消费事件：${event.actionName()}")
        return touchEvent
    }
}