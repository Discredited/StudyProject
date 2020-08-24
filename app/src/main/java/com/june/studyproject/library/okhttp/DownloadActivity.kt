package com.june.studyproject.library.okhttp

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.june.network.download.DownloadHelper
import com.june.network.download.ProgressListener
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.base.ext.loadImage
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.common.FilePathHelper
import com.june.studyproject.common.Toast
import com.june.studyproject.expand.zip.UnzipActivity
import kotlinx.android.synthetic.main.activity_ok_http_download.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File

class DownloadActivity : BaseActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_ok_http_download

    override fun initView() {
        btDownload.click {
            //检查文件读写权限
            //checkStoragePermission()
            startDownload()
        }
        btUnzip.click {
            startActivity(Intent(this, UnzipActivity::class.java))
        }
    }

    override fun loadData() {
        val appPath = PathUtils.getInternalAppDataPath()
        val imageUrl = "$appPath/1597415289310.jpg"
        Timber.e("imageUrl:$imageUrl")
        val file = File(imageUrl)
        if (file.exists()) {
            ivImage.loadImage(imageUrl)
        }

        tvDownloadProgress.text = "0 / 0"
    }

    private fun checkStoragePermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE)
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(granted: MutableList<String>) {
                    startDownload()
                }

                override fun onDenied(deniedForever: MutableList<String>,
                                      denied: MutableList<String>) {
                    ToastUtils.showShort("StudyProject需要文件读写权限")
                }
            })
            .request()
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
                        tvDownloadProgress.text = "$progress / $max"
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