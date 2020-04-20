package com.june.studyproject.expand.image.box

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.children
import com.june.imageabout.box.ImageBoxLayout
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.common.ConstHelper
import kotlinx.android.synthetic.main.activity_image_box_layout.*
import timber.log.Timber

class ImageBoxLayoutActivity : BaseActivity() {

    private lateinit var mImageBoxLayout: ImageBoxLayout<MediaVo>
    private val mRadiusSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            mImageBoxLayout.setImageRadius(seekBar.progress.toFloat())
            mImageBoxLayout.invalidate()
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_image_box_layout

    override fun initView() {
        mImageBoxLayout = findViewById(R.id.vImageBoxLayout)
        mImageBoxLayout.setImageLoader(ImageLoader())

        //设置列数
        val spinnerItems = arrayOf("2", "3", "4")
        val columnAdapter = ArrayAdapter(this, R.layout.item_spinner_text, spinnerItems)
        spinnerColumn.adapter = columnAdapter
        spinnerColumn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val column = position + 1
                mImageBoxLayout.setExpectColumn(column)
                mImageBoxLayout.requestLayout()
            }
        }

        //设置FourStyle
        rgStyleGroup.setOnCheckedChangeListener { group, checkedId ->
            Timber.i("checkId:$checkedId")
            group.children.forEachIndexed { index, view ->
                if (view is AppCompatRadioButton) {
                    if (view.isChecked) {
                        val fourStyle = if (index == 0) {
                            ImageBoxLayout.FOUR_STYLE_NORMAL
                        } else {
                            ImageBoxLayout.FOUR_STYLE_SQUARE
                        }
                        mImageBoxLayout.setFourStyle(fourStyle)
                        mImageBoxLayout.requestLayout()
                    }
                }
            }
        }

        //设置Radius
        sbImageGapSeek.setOnSeekBarChangeListener(mRadiusSeekBarListener)
    }

    override fun loadData() {
        val random = (Math.random() * 20).toInt()
        val diffImage = ConstHelper.getDiffImage(random)
        val list = diffImage.map {
            MediaVo(it, it, 0, 0)
        }.toMutableList()
        mImageBoxLayout.setImageList(list)
    }
}