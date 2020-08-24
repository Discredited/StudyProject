package com.june.studyproject.expand.zip

import com.blankj.utilcode.util.ZipUtils
import java.io.File

object ZipHelper {

    /**
     * 解压文件
     */
    fun unzip(zipFilePath: String, unzipFilePath: String): MutableList<File> {
        return ZipUtils.unzipFile(zipFilePath, unzipFilePath)
    }
}