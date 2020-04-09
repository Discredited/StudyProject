package com.june.imageabout.watcher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.june.imageabout.R
import com.june.imageabout.vo.ImageVo
import kotlinx.android.synthetic.main.activity_image_watch.*

/**
 * 大图展示页面
 * 需要自己注册ImageWatchActivity，方便配置Activity的Style
 */
class ImageWatchActivity : AppCompatActivity() {

    private val adapter: ImageWatchAdapter = ImageWatchAdapter()
    private val mPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            tvImagePosition?.text = "${position + 1} / ${adapter.itemCount}"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_watch)

        vpImageWatch.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vpImageWatch.adapter = adapter
        vpImageWatch.registerOnPageChangeCallback(mPageChangeListener)

        val imageList = intent.getParcelableArrayListExtra<ImageVo>("IMAGE_LIST")
        imageList?.let {
            adapter.setImageList(it)
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
        fun starter(context: Context, list: MutableList<ImageVo>, position: Int) {
            val intent = Intent(context, ImageWatchActivity::class.java)
            val arrayList = arrayListOf<ImageVo>()
            list.forEach { arrayList.add(it) }
            intent.putExtra("IMAGE_LIST", arrayList)
            intent.putExtra("IMAGE_POSITION", position)
            context.startActivity(intent)
        }
    }
}