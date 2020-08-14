package com.june.studyproject.library.okhttp

import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.june.network.DownloadHelper
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.common.Toast
import kotlinx.android.synthetic.main.activity_ok_http_download.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DownloadActivity : BaseActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_ok_http_download

    override fun initView() {
        btDownload.click {
            //检查文件读写权限
            checkStoragePermission()
        }
    }

    override fun loadData() {
    }

    private fun checkStoragePermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE)
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(granted: MutableList<String>) {
                    startDownload()
                }

                override fun onDenied(deniedForever: MutableList<String>, denied: MutableList<String>) {
                    ToastUtils.showShort("StudyProject需要文件读写权限")
                }
            })
            .request()
    }

    private fun startDownload() {
        val videoUrl = ConstHelper.getDiffVideos(1)[0]
        Timber.e("videoUrl:$videoUrl")
        val appPath = PathUtils.getInternalAppDataPath()
        Timber.e("appPath:$appPath")
        val fileName = "${System.currentTimeMillis()}.mp4"
        Timber.e("fileName:$fileName")
        val filePath = "${appPath}/${fileName}"
        Timber.e("filePath:$filePath")

        val downloadHelper = DownloadHelper()
        lifecycleScope.launch {
            val downloadResult = withContext(Dispatchers.IO) {
                downloadHelper.startDownload(videoUrl, filePath)
            }
            downloadResult?.let {
                Timber.e("save success:$it")
                Toast.showShort("save success:$it")
            }
        }
    }
}