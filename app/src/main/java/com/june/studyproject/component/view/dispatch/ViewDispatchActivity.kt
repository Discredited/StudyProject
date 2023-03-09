package com.june.studyproject.component.view.dispatch

import android.content.Context
import android.content.Intent
import android.view.MotionEvent
import com.june.base.basic.ext.click
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.databinding.ActivityViewDispatchBinding
import timber.log.Timber

/**
 * View 事件分发
 */
class ViewDispatchActivity : StudyBaseActivity<ActivityViewDispatchBinding>() {

    override fun initView() {
        mBinding.vLayout.click { Timber.i("点击了Layout") }
        mBinding.vView.click { Timber.i("点击了View") }
    }

    override fun loadData() {
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Timber.i("Activity dispatchTouchEvent 分发事件：${event.actionName()}")
        val dispatchTouchEvent = super.dispatchTouchEvent(event)
        //Timber.i("Activity dispatchTouchEvent  $dispatchTouchEvent 分发事件：${event.actionName()}")
        return dispatchTouchEvent
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Timber.i("Activity onTouchEvent 消费事件：${event.actionName()}")
        val touchEvent = super.onTouchEvent(event)
        //Timber.i("Activity onTouchEvent $touchEvent 消费事件：${event.actionName()}")
        return touchEvent
    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, ViewDispatchActivity::class.java))
        }

    }
}