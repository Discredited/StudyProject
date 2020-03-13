package com.june.studyproject.thread.rxjava

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.june.studyproject.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxJavaActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)

        loadData()
    }

    private fun loadData() {
        disposable = Observable.create<String> { emitter ->
            emitter.onNext("林飞")
            emitter.onNext("甘柠真")
            emitter.onNext("海姬")
            emitter.onNext("鸠丹媚")
        }
                .map { s ->
                    Log.e("sherry", Thread.currentThread().name)
                    "$s-1"
                }
                //多次调用subscribeOn 只有第一次(严格意义上说应该是距离数据源上游最近的一次)调度配置会生效
                //因为链式调用是自上而下的  Observable -> Observer
                //但是订阅过程是自下而上的  Observer -> Observable
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ s ->
                    Log.e("sherry", s)
                }) { throwable ->
                    Log.e("sherry", throwable.message ?: "")
                }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    companion object {

        @JvmStatic
        fun starter(context: Context) {
            context.startActivity(Intent(context, RxJavaActivity::class.java))
        }
    }
}