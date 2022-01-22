package com.june.studyproject.library.okhttp

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.PathUtils
import com.june.base.basic.ext.click
import com.june.base.basic.part.BaseActivity
import com.june.network.download.DownloadHelper
import com.june.network.download.ProgressListener
import com.june.studyproject.base.ext.loadImage
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.common.FilePathHelper
import com.june.studyproject.common.Toast
import com.june.studyproject.databinding.ActivityOkHttpDownloadBinding
import com.june.studyproject.expand.zip.UnzipActivityBasic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File

class DownloadActivity : BaseActivity<ActivityOkHttpDownloadBinding>() {

    override fun initView() {
        mBinding.btDownload.click {
            //检查文件读写权限
            //checkStoragePermission()
            startDownload()
        }
        mBinding.btUnzip.click {
            startActivity(Intent(this, UnzipActivityBasic::class.java))
        }
    }

    override fun loadData() {
        val appPath = PathUtils.getInternalAppDataPath()
        val imageUrl = "$appPath/1597415289310.jpg"
        Timber.e("imageUrl:$imageUrl")
        val file = File(imageUrl)
        if (file.exists()) {
            mBinding.ivImage.loadImage(imageUrl)
        }

        mBinding.tvDownloadProgress.text = "0 / 0"
    }

    private fun startDownload() {
        val zipUrl = ConstHelper.getLolZip()

        val appPath = FilePathHelper.appExternalDataPath()
        Timber.e("appPath:$appPath")

        val fileName = "LeagueOfLegendsIcon.zip"
        Timber.e("fileName:$fileName")
        val filePath = "${appPath}/${fileName}"
        Timber.e("filePath:$filePath")

        val downloadHelper = DownloadHelper(object : ProgressListener {
            override fun onProgress(progress: Long, max: Long, percent: Float) {
                Timber.e("下载进度:progress:$progress    max:$max    percent:$percent")
                lifecycleScope.launch {
                    withContext(Dispatchers.Main) {
                        mBinding.tvDownloadProgress.text = "$progress / $max"
                    }
                }
            }
        })
        lifecycleScope.launch {
            val downloadResult = withContext(Dispatchers.IO) {
                downloadHelper.startDownload(zipUrl, filePath)
            }
            downloadResult?.let {
                Timber.e("save success:$it")
                Toast.showShort("save success:$it")
            }
            if (downloadResult == null) {
                Toast.showShort("download GG")
            }
        }
    }
}