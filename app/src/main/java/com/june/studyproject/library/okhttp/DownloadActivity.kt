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
import com.june.studyproject.base.ext.loadImage
import com.june.studyproject.common.FilePathHelper
import com.june.studyproject.common.Toast
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
    }

    override fun loadData() {
        val appPath = PathUtils.getInternalAppDataPath()
        val imageUrl = "$appPath/1597415289310.jpg"
        Timber.e("imageUrl:$imageUrl")
        val file = File(imageUrl)
        if (file.exists()) {
            ivImage.loadImage(imageUrl)
        }
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
        //val videoUrl = ConstHelper.getDiffVideos(1)[0]
        //Timber.e("videoUrl:$videoUrl")
        val imageUrl = "http://img.ewebweb.com/uploads/20191127/13/1574832894-rPXLSjDWIn.jpg"

        val appPath = FilePathHelper.appExternalDataPath()
        Timber.e("appPath:$appPath")

//        val file = File(appPath)
//        Timber.e("当前文件目录：${file.name}")
//        if (file.isDirectory) {
//            file.listFiles()?.forEach {
//                Timber.e("--子目录：${it.absolutePath}")
//                if (it.name == "1597415289310.jpg") {
//                    Timber.e("待删除文件：${it.absolutePath}")
//                    if (it.exists()) {
//                        val delete = deleteFile(it.name)
//                        Timber.e("删除文件：$it        $delete")
//                    } else {
//                        Timber.e("文件不存在")
//                    }
//                }
//            }
//        }

        val fileName = "${System.currentTimeMillis()}.jpg"
        Timber.e("fileName:$fileName")
        val filePath = "${appPath}/${fileName}"
        Timber.e("filePath:$filePath")

        val downloadHelper = DownloadHelper()
        lifecycleScope.launch {
            val downloadResult = withContext(Dispatchers.IO) {
                downloadHelper.startDownload(imageUrl, filePath)
            }
            downloadResult?.let {
                Timber.e("save success:$it")
                Toast.showShort("save success:$it")
            }
        }
    }
}