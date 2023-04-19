package com.june.studyproject.expand.wallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.lifecycle.lifecycleScope
import com.june.base.basic.ext.click
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.glide.GlideApp
import com.june.studyproject.common.Toast
import com.june.studyproject.databinding.ActivityWallpaperBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * 壁纸页面
 *
 * 2023/4/18
 * @author June
 */
class WallpaperActivity : StudyBaseActivity<ActivityWallpaperBinding>() {

    private val wallpaperUrl = "https://img1.baidu.com/it/u=3724107547,2991731744&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"

    override fun initView() {
        mBinding.btSetWallpaper.click {
            setWallpaper()
        }
    }

    override fun loadData() {
        wallpaperEnable()

        GlideApp.with(this)
            .load(wallpaperUrl)
            .into(mBinding.ivWallpaper)
    }

    private fun wallpaperEnable() {
        val wallpaperManager = getSystemService(WALLPAPER_SERVICE) as WallpaperManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Timber.e("是否支持wallpaper:${wallpaperManager.isWallpaperSupported}")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Timber.e("是否支持设置wallpaper:${wallpaperManager.isSetWallpaperAllowed}")
        }
    }

    private fun setWallpaper() {
        lifecycleScope.launch(Dispatchers.Main) {
            val bitmap: Bitmap? = withContext(Dispatchers.IO) {
                val file = GlideApp.with(this@WallpaperActivity)
                    .downloadOnly()
                    .load(wallpaperUrl)
                    .submit()
                    .get()

                if (!file.exists()) {
                    val toast = "资源文件不存在 ${file.absoluteFile}"
                    Toast.showShort(toast)
                    Timber.e(toast)
                    return@withContext null
                }

                BitmapFactory.decodeFile(file.absolutePath)
            }

            if (null == bitmap) {
                Timber.e("设置壁纸失败")
            } else {
                val wallpaperManager = getSystemService(WALLPAPER_SERVICE) as WallpaperManager
                wallpaperManager.setBitmap(bitmap)
            }
        }
    }
}