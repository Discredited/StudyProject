package com.june.studyproject.component.recycler.custom

import android.view.View
import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import github.hellocsl.layoutmanager.gallery.GalleryLayoutManager
import kotlinx.android.synthetic.main.activity_custom_layout_manager.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import timber.log.Timber
import kotlin.math.abs


class CustomLayoutManagerActivity : BaseActivity() {

    private lateinit var adapter: CustomLayoutManagerAdapter

    override fun getLayoutResId(): Int = R.layout.activity_custom_layout_manager

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = CustomLayoutManagerAdapter()

        val galleryLayoutManager = GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL)
        galleryLayoutManager.attach(rvCard)
        galleryLayoutManager.setItemTransformer(ScaleTransformer())
        rvCard.adapter = adapter
        rvCard.setHasFixedSize(true)
    }

    override fun loadData() {
        adapter.setNewData(mutableListOf(
            ContextCompat.getColor(this, R.color.color_style_1_1),
            ContextCompat.getColor(this, R.color.color_style_1_2),
            ContextCompat.getColor(this, R.color.color_style_1_3),
            ContextCompat.getColor(this, R.color.color_style_1_4),
            ContextCompat.getColor(this, R.color.color_style_1_5)
        ))
    }

    inner class ScaleTransformer : GalleryLayoutManager.ItemTransformer {

        override fun transformItem(layoutManager: GalleryLayoutManager, item: View, fraction: Float) {
            if (layoutManager.orientation == GalleryLayoutManager.VERTICAL) {
                return
            }
            val width = item.width
            val height = item.height

            item.pivotX = width / 2f
            item.pivotY = height.toFloat()

            val scale = 1 - 0.05f * abs(fraction)
            Timber.e("scale:$scale    fraction:$fraction")
            item.scaleX = scale
            item.scaleY = scale
        }
    }
}