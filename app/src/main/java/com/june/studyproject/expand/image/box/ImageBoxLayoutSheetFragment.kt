package com.june.studyproject.expand.image.box

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.june.imageabout.box.ImageBoxLayout
import com.june.studyproject.R
import com.june.studyproject.common.ConstHelper
import kotlinx.android.synthetic.main.fragment_image_box_layout.*
import timber.log.Timber

class ImageBoxLayoutSheetFragment : BottomSheetDialogFragment() {

    private var mImageBoxLayout: ImageBoxLayout<MediaVo>? = null

    private val mRadiusSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            mImageBoxLayout?.setImageRadius(seekBar.progress.toFloat())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image_box_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置列数
        val spinnerItems = arrayOf("2", "3", "4")
        val columnAdapter = ArrayAdapter(requireActivity(), R.layout.item_spinner_text, spinnerItems)
        spinnerColumn.adapter = columnAdapter
        spinnerColumn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val column = position + 2
                mImageBoxLayout?.setExpectColumn(column)
                mImageBoxLayout?.requestLayout()
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
                        mImageBoxLayout?.setFourStyle(fourStyle)
                        mImageBoxLayout?.requestLayout()
                    }
                }
            }
        }

        //设置Radius
        sbImageGapSeek.setOnSeekBarChangeListener(mRadiusSeekBarListener)

        //设置更新数据源
        tvImageCount.setOnClickListener {
            mImageBoxLayout?.let {
                ConstHelper.getDiffImage((Math.random() * 20).toInt())
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mImageBoxLayout = activity?.findViewById(R.id.vImageBoxLayout)
        tvImageCount.text = "当前生成了${mImageBoxLayout?.getImageList()?.size ?: 0}张图片"
    }

    override fun onDestroyView() {
        mImageBoxLayout = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): ImageBoxLayoutSheetFragment {
            return ImageBoxLayoutSheetFragment()
        }
    }
}