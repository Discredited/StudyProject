package com.june.studyproject.expand.image.box

import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.june.imageabout.box.ImageBoxLayout
import com.june.studyproject.R
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.databinding.ActivityImageBoxLayoutBinding
import timber.log.Timber

class ImageBoxLayoutActivity : BaseActivity<ActivityImageBoxLayoutBinding>() {

    private var mBottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null
    private lateinit var mImageBoxLayout: ImageBoxLayout<MediaVo>

    override fun initView() {
        mImageBoxLayout = findViewById(R.id.vImageBoxLayout)
        mImageBoxLayout.setImageLoader(ImageLoader())

        mBinding.tlLayout.toolbar.apply {
            initToolbar(javaClass.simpleName, R.menu.menu_more_black)
            setNavigationOnClickListener { onBackPressed() }
            setOnMenuItemClickListener {
                //open panel
                mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                true
            }
        }

        mBottomSheetBehavior = BottomSheetBehavior.from(mBinding.flBottomSheetContainer)
        mBottomSheetBehavior?.apply {
            state = BottomSheetBehavior.STATE_HIDDEN
            setUpdateImportantForAccessibilityOnSiblings(true)
            isFitToContents = true
            // halfExpandedRatio = 0.4f
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    Timber.e("slideOffset:$slideOffset")
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    Timber.e("newState:$newState")
                }
            })
        }
    }

    override fun loadData() {
        val fragment = ImageBoxLayoutSheetFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.flBottomSheetContainer, fragment, "ImageBoxLayoutSheetFragment")
                .show(fragment)
                .commitNow()

        val random = (Math.random() * 20).toInt()
        val diffImage = ConstHelper.getDiffImage(random)
        val list = diffImage.map {
            MediaVo(it, it, 0, 0)
        }.toMutableList()
        mImageBoxLayout.setImageList(list)
    }
}