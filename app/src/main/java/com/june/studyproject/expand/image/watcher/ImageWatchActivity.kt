package com.june.studyproject.expand.image.watcher

import android.content.Context
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.june.imageabout.watcher.OnImageDragListener
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.expand.image.box.MediaVo
import kotlinx.android.synthetic.main.activity_image_watch.*

/**
 * 大图展示页面
 * 需要自己注册ImageWatchActivity，方便配置Activity的Style
 */
class ImageWatchActivity : BaseActivity() {

    private val adapter: ImageWatchAdapter = ImageWatchAdapter()
    private val mPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            tvImagePosition?.text = "${position + 1} / ${adapter.itemCount}"
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_image_watch

    override fun initView() {
        adapter.setImageDragListener(object : OnImageDragListener {
            override fun onDragStateChange(state: Int, x: Float, y: Float) {
            }

            override fun onDragOverThreshold() {
                onBackPressed()
            }
        })

        vpImageWatch.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vpImageWatch.adapter = adapter
        vpImageWatch.registerOnPageChangeCallback(mPageChangeListener)
    }

    override fun loadData() {
        val imageList = intent.getParcelableArrayListExtra<MediaVo>("IMAGE_LIST")
        imageList?.let {
            adapter.setNewData(it)
        }
        val position = intent.getIntExtra("IMAGE_POSITION", 0)
        //smoothScroll false去掉翻页时的动画
        if (position > 0) {
            vpImageWatch.setCurrentItem(position, false)
        }
    }

    override fun onDestroy() {
        vpImageWatch.unregisterOnPageChangeCallback(mPageChangeListener)
        super.onDestroy()
    }

    companion object {
        fun starter(context: Context, list: MutableList<MediaVo>, position: Int) {
            val intent = Intent(context, ImageWatchActivity::class.java)
            val arrayList = arrayListOf<MediaVo>()
            list.forEach { arrayList.add(it) }
            intent.putExtra("IMAGE_LIST", arrayList)
            intent.putExtra("IMAGE_POSITION", position)
            context.startActivity(intent)
        }
    }
}