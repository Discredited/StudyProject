package com.june.studyproject.component.permission

import android.Manifest
import androidx.core.app.ActivityCompat
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.click
import kotlinx.android.synthetic.main.activity_permissions.*

class PermissionsActivityBasic : BasicActivity() {

    override fun getLayoutResId() = R.layout.activity_permissions

    override fun initView() {
        tvPermissions.click { requestPermissionsByAndroidUtils() }
        //tvPermissions.click { requestPermissionsByNative() }
    }

    override fun loadData() {
        tvPermissions.append("\n")
        tvPermissions.append("当前请求相机权限")
        tvPermissions.append("\n")
        tvPermissions.append("当前请求录音权限")
        tvPermissions.append("\n")
    }

    private fun requestPermissionsByNative() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
            100
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            tvPermissions.append("\n")
            tvPermissions.append("已允许 ${grantResults.size}")
            grantResults.forEach {
                tvPermissions.append("\n")
                tvPermissions.append("已允许 $it")
            }
        }
    }

    private fun requestPermissionsByAndroidUtils() {
        PermissionUtils.permission(
            PermissionConstants.MICROPHONE,
            PermissionConstants.CAMERA
        ).callback(object : PermissionUtils.FullCallback {
            override fun onGranted(granted: MutableList<String>) {
                granted.forEach {
                    tvPermissions.append("\n")
                    tvPermissions.append("已允许 $it")
                }
            }

            override fun onDenied(deniedForever: MutableList<String>, denied: MutableList<String>) {
                deniedForever.forEach {
                    tvPermissions.append("\n")
                    tvPermissions.append("永久拒绝 $it")
                }
                denied.forEach {
                    tvPermissions.append("\n")
                    tvPermissions.append("本次拒绝 $it")
                }
            }
        }).request()
    }
}