package com.june.studyproject.common

import com.blankj.utilcode.util.PathUtils

/**
 * 关于Android文件管理和存储目录
 */
object FilePathHelper {

    /**
     *  App内部储存私有目录
     *  内部储存不是内存，也是设备磁盘中一个可以存储数据的地方，只是这个地方不会对外暴露，只有设备root以后才可见
     *  该目录下面的内容仅供App自身访问，且不需要动态申请访问权限，当App卸载时，目录下的文件会自动删除
     *  该目录下面的内容不会对面公开，其他app无法访问
     *  该目录下一般用于存放应用存放私密信息，非公开数据内容
     *
     *  路径:/data/data/0/packageName(com.xxx.xxx) 或者 /data/user/0/packageName(com.xxx.xxx)
     */
    fun appInternalDataPath(): String {
        return PathUtils.getInternalAppDataPath()
    }

    /**
     *  App外部存储私有目录
     *  一般项目安装后就会在/storage/emulated/0/Android/data/目录下创建package文件夹(也有说不会自动创建...)
     *  从Android4.4以后App自身访问该目录是不需要动态申请存储访问权限的，当App卸载时，目录下的文件会自动删除
     *  其他App访问该目录需要动态申请存储访问权限
     *  该目录下一般用于存放非私密信息，可公开数据内容
     *
     *  路径:/storage/emulated/0/Android/data/packageName(com.xxx.xxx)
     */
    fun appExternalDataPath(): String {
        return PathUtils.getExternalAppDataPath()
    }

    /**
     * 获取Sd卡根目录
     *
     * 路径:/storage/emulated/0/
     */
    fun externalStoragePath(): String {
        return PathUtils.getExternalStoragePath()
    }
}