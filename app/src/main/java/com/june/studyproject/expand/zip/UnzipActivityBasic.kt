package com.june.studyproject.expand.zip

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.base.ext.setGridManager
import com.june.studyproject.common.FilePathHelper
import com.june.studyproject.common.GridItemDecoration
import com.june.studyproject.common.Toast
import kotlinx.android.synthetic.main.activity_unzip.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Zip解压
 */
class UnzipActivityBasic : BasicActivity() {

    private val mAdapter = UnzipAdapter()

    override fun getLayoutResId(): Int = R.layout.activity_unzip

    override fun initView() {
        rvSkillIcon.setGridManager(3)
        rvSkillIcon.adapter = mAdapter
        rvSkillIcon.setHasFixedSize(true)
        rvSkillIcon.addItemDecoration(GridItemDecoration())

        btUnzip.click {
            //开始解压文件
            it.visibility = View.GONE
            unzip()
        }
    }

    override fun loadData() {
    }

    private fun unzip() {
        //获取目录下的压缩文件
        val appExternalDataPath = FilePathHelper.appExternalDataPath()
        val zipName = "temp.zip"
        val file = File("$appExternalDataPath/$zipName")
        if (!file.exists()) {
            //待解压文件不存在
            Toast.showShort("带解压文件不存在")
            return
        }

        //创建解压文件路径
        val unzipPath = "$appExternalDataPath/iconResource"
        tvUnzipProgress.visibility = View.VISIBLE
        tvUnzipProgress.text = getString(R.string.str_unzip_progress)
        lifecycleScope.launch {
            val list = withContext(Dispatchers.IO) {
                ZipHelper.unzip(file.absolutePath, unzipPath).map { it.absolutePath }.toMutableList()
            }
            tvUnzipProgress.visibility = View.GONE
            if (list.isNotEmpty()) {
                mAdapter.setNewInstance(list)
            } else {
                btUnzip.visibility = View.VISIBLE
                Toast.showShort("当前没有解压文件")
            }
        }
    }
}