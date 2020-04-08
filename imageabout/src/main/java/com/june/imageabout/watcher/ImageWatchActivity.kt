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
 * 大图展示控件
 */
class ImageWatchActivity : AppCompatActivity() {

    private val adapter: ImageWatchAdapter = ImageWatchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_watch)
        ImageWatcherHelper.instance.mImageLoader?.let {
            adapter.setImageLoader(it)
        }
        vpImageWatch.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vpImageWatch.adapter = adapter

        val imageList = intent.getParcelableArrayListExtra<ImageVo>("IMAGE_LIST")
        imageList?.let {
            adapter.setImageList(it)
        }
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