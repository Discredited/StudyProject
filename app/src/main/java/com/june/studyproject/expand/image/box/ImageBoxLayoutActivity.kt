package com.june.studyproject.expand.image.box

import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.june.imageabout.box.ImageBoxLayout
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.common.ConstHelper
import kotlinx.android.synthetic.main.activity_image_box_layout.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import timber.log.Timber

class ImageBoxLayoutActivity : BaseActivity() {

    private var mBottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null
    private lateinit var mImageBoxLayout: ImageBoxLayout<MediaVo>

    override fun getLayoutResId(): Int = R.layout.activity_image_box_layout

    override fun initView() {
        mImageBoxLayout = findViewById(R.id.vImageBoxLayout)
        mImageBoxLayout.setImageLoader(ImageLoader())

        toolbar.initToolbar(javaClass.simpleName, R.menu.menu_more_black)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setOnMenuItemClickListener {
            //open panel
            mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            true
        }

        mBottomSheetBehavior = BottomSheetBehavior.from(flBottomSheetContainer)
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        mBottomSheetBehavior?.setUpdateImportantForAccessibilityOnSiblings(true)
        mBottomSheetBehavior?.isFitToContents = true
        mBottomSheetBehavior?.halfExpandedRatio = 0.4f
        mBottomSheetBehavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Timber.e("slideOffset:$slideOffset")
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Timber.e("newState:$newState")
            }
        })
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