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
import com.june.imageabout.box.ImageBoxLayout
import com.june.studyproject.R
import com.june.base.basic.part.BaseBottomSheetDialogFragment
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.databinding.FragmentImageBoxLayoutBinding
import timber.log.Timber

class ImageBoxLayoutSheetFragment : BaseBottomSheetDialogFragment<FragmentImageBoxLayoutBinding>() {

    private var mImageBoxLayout: ImageBoxLayout<MediaVo>? = null

    //间距
    private val mGapSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            mImageBoxLayout?.setImageGap(seekBar.progress)
            mImageBoxLayout?.requestLayout()
        }
    }

    //圆角
    private val mRadiusSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            mImageBoxLayout?.setImageRadius(seekBar.progress.toFloat())
        }
    }

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentImageBoxLayoutBinding {
        return FragmentImageBoxLayoutBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        //设置列数
        val spinnerItems = arrayOf("2", "3", "4")
        val columnAdapter = ArrayAdapter(requireActivity(), R.layout.item_spinner_text, spinnerItems)
        mBinding.spinnerColumn.adapter = columnAdapter
        mBinding.spinnerColumn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val column = position + 2
                mImageBoxLayout?.setExpectColumn(column)
                mImageBoxLayout?.requestLayout()
            }
        }

        //设置FourStyle
        mBinding.rgStyleGroup.setOnCheckedChangeListener { group, checkedId ->
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

        //设置Gap
        mBinding.sbImageGapSeek.setOnSeekBarChangeListener(mGapSeekBarListener)

        //设置Radius
        mBinding.sbImageRadiusSeek.setOnSeekBarChangeListener(mRadiusSeekBarListener)

        //设置更新数据源
        mBinding.tvImageCount.setOnClickListener {
            mImageBoxLayout?.let {
                val diffImage = ConstHelper.getDiffImage((Math.random() * 20).toInt())
                val list = diffImage.map { url ->
                    MediaVo(url, url, 0, 0)
                }.toMutableList()
                it.setImageList(list)
                mBinding.tvImageCount.text = getString(R.string.image_box_current_images, list.size)
            }
        }

        mBinding.cbImageMaxLimit.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mImageBoxLayout?.setImageMax(9)
                mImageBoxLayout?.getImageList()?.let {
                    mImageBoxLayout?.setImageList(it)
                }
            } else {
                mImageBoxLayout?.setImageMax(-1)
                mImageBoxLayout?.getImageList()?.let {
                    mImageBoxLayout?.setImageList(it)
                }
            }
        }

        mBinding.cbImageMaxCover.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mImageBoxLayout?.setImageMax(9)
            } else {
                mImageBoxLayout?.setImageMax(-1)
            }

            mImageBoxLayout?.setImageMaxCover(isChecked)
            mImageBoxLayout?.getImageList()?.let {
                mImageBoxLayout?.setImageList(it)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mImageBoxLayout = activity?.findViewById(R.id.vImageBoxLayout)
        mBinding.tvImageCount.text = getString(R.string.image_box_current_images, mImageBoxLayout?.getImageList()?.size
                ?: 0)
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