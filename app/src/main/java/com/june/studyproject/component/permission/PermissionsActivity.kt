package com.june.studyproject.component.permission

import android.Manifest
import androidx.core.app.ActivityCompat
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.databinding.ActivityPermissionsBinding

class PermissionsActivity : BaseActivity<ActivityPermissionsBinding>() {

    override fun viewBinding(): ActivityPermissionsBinding {
        return ActivityPermissionsBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mBinding.tvPermissions.click { requestPermissionsByAndroidUtils() }
    }

    override fun loadData() {
        mBinding.tvPermissions.apply {
            append("\n")
            append("当前请求相机权限")
            append("\n")
            append("当前请求录音权限")
            append("\n")
        }
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
            mBinding.tvPermissions.append("\n")
            mBinding.tvPermissions.append("已允许 ${grantResults.size}")
            grantResults.forEach {
                mBinding.tvPermissions.append("\n")
                mBinding.tvPermissions.append("已允许 $it")
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
                    mBinding.tvPermissions.append("\n")
                    mBinding.tvPermissions.append("已允许 $it")
                }
            }

            override fun onDenied(deniedForever: MutableList<String>, denied: MutableList<String>) {
                deniedForever.forEach {
                    mBinding.tvPermissions.append("\n")
                    mBinding.tvPermissions.append("永久拒绝 $it")
                }
                denied.forEach {
                    mBinding.tvPermissions.append("\n")
                    mBinding.tvPermissions.append("本次拒绝 $it")
                }
            }
        }).request()
    }
}