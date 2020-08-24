package com.june.studyproject.common

object ConstHelper {

    private val IMAGE_SOURCE_ALL = arrayOf(
        "http://www.jshddq.net/UploadFiles/img_3_3560281984_1977672327_26.jpg",
        "http://img0.imgtn.bdimg.com/it/u=3058041753,3990052783&fm=15&gp=0.jpg",
        "http://img4.imgtn.bdimg.com/it/u=3318550662,3589021928&fm=26&gp=0.jpg",
        "http://img4.imgtn.bdimg.com/it/u=2288838084,1662905828&fm=214&gp=0.jpg",

        "http://img1.imgtn.bdimg.com/it/u=3617785790,3731361837&fm=26&gp=0.jpg",
        "http://img2.imgtn.bdimg.com/it/u=407514011,2250260535&fm=26&gp=0.jpg",
        "http://tupian.qqw21.com/article/UploadPic/2020-3/20203152257648414.jpg",
        "http://img0.imgtn.bdimg.com/it/u=3644175430,362551032&fm=214&gp=0.jpg",
        "http://img.improve-yourmemory.com/pic/4b97187aa32bd662c09ff25ab5ea2f03-0.jpg",

        "http://www.yozrun.com/UploadFiles/img_1_3281518170_2505144515_26.jpg",
        "http://img.qqzhi.com/upload/img_3_2537823874D1901268111_27.jpg",
        "http://img3.imgtn.bdimg.com/it/u=3457130936,774247519&fm=26&gp=0.jpg",
        "http://img0.imgtn.bdimg.com/it/u=3644175430,362551032&fm=214&gp=0.jpg",
        "http://img.ewebweb.com/uploads/20191127/13/1574832894-rPXLSjDWIn.jpg",

        "http://img1.imgtn.bdimg.com/it/u=1441467321,2789170753&fm=214&gp=0.jpg",
        "http://tupian.qqw21.com/article/UploadPic/2020-3/2020371171838593.jpg",
        "http://www.jshddq.net/UploadFiles/img_2_2304607039_3307652783_26.jpg",
        "http://tupian.qqw21.com/article/UploadPic/2020-3/2020371171913337.jpg",
        "http://uploads.5068.com/allimg/1801/82-1P123151011-50.jpg",

        //GIF
        "http://imgsrc.baidu.com/forum/w=580/sign=35ffe1078735e5dd902ca5d746c7a7f5/79fcc758ccbf6c81fa372dbdbe3eb13532fa40e5.gif",
        "https://b-ssl.duitang.com/uploads/item/201410/13/20141013151749_JH2rE.gif",
        "http://s9.rr.itc.cn/r/wapChange/20168_6_16/a37qr04592277918185.jpg",
        "http://imgsrc.baidu.com/forum/w=580/sign=c596754eb6b7d0a27bc90495fbee760d/53ac09e93901213fd0ac572050e736d12d2e95ef.jpg",
        "http://a.hiphotos.baidu.com/exp/w=500/sign=7a6bb544fad3572c66e29cdcba126352/1b4c510fd9f9d72a10e3f2b0dc2a2834359bbb9c.jpg",

        //长图
        "http://5b0988e595225.cdn.sohucs.com/images/20181221/b7908797d2d545f89eb3042be3527d40.jpeg",
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586409907566&di=bbf1541920e117dedc26f89b264d94e6&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ffceae41137314caa5be367596b8b988bf25bd137774da-oS8MH0_fw658",
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586410019740&di=a58855f2e7f733321945db5b3a7d91ba&imgtype=0&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D1722457218%2C2204835954%26fm%3D214%26gp%3D0.jpg",
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586410114053&di=8aefcb2b48debde4cca368c732453e0d&imgtype=0&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D3690672463%2C4117611042%26fm%3D214%26gp%3D0.jpg",
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586410266900&di=8dc6f8c38dcc1592180d69e49a10a7ef&imgtype=0&src=http%3A%2F%2Fwx4.sinaimg.cn%2Forj360%2F67ff7977ly1gdf6ljst1fj20ly2jzkjl.jpg"
    )

    val IMAGE_SOURCE_GIF = arrayOf(
        "http://www.jshddq.net/UploadFiles/img_3_3560281984_1977672327_26.jpg",
        "http://img0.imgtn.bdimg.com/it/u=3058041753,3990052783&fm=15&gp=0.jpg",
        "http://img4.imgtn.bdimg.com/it/u=3318550662,3589021928&fm=26&gp=0.jpg",
        "http://img4.imgtn.bdimg.com/it/u=2288838084,1662905828&fm=214&gp=0.jpg",

        "http://img1.imgtn.bdimg.com/it/u=3617785790,3731361837&fm=26&gp=0.jpg",
        "http://img2.imgtn.bdimg.com/it/u=407514011,2250260535&fm=26&gp=0.jpg",
        "http://tupian.qqw21.com/article/UploadPic/2020-3/20203152257648414.jpg",
        "http://img0.imgtn.bdimg.com/it/u=3644175430,362551032&fm=214&gp=0.jpg",
        "http://img.improve-yourmemory.com/pic/4b97187aa32bd662c09ff25ab5ea2f03-0.jpg",

        "http://www.yozrun.com/UploadFiles/img_1_3281518170_2505144515_26.jpg",
        "http://img.qqzhi.com/upload/img_3_2537823874D1901268111_27.jpg",
        "http://img3.imgtn.bdimg.com/it/u=3457130936,774247519&fm=26&gp=0.jpg",
        "http://img0.imgtn.bdimg.com/it/u=3644175430,362551032&fm=214&gp=0.jpg",
        "http://img.ewebweb.com/uploads/20191127/13/1574832894-rPXLSjDWIn.jpg",

        "http://img1.imgtn.bdimg.com/it/u=1441467321,2789170753&fm=214&gp=0.jpg",
        "http://tupian.qqw21.com/article/UploadPic/2020-3/2020371171838593.jpg",
        "http://www.jshddq.net/UploadFiles/img_2_2304607039_3307652783_26.jpg",
        "http://tupian.qqw21.com/article/UploadPic/2020-3/2020371171913337.jpg",
        "http://uploads.5068.com/allimg/1801/82-1P123151011-50.jpg"
    )

    val VIDEO_SOURCE = arrayOf(
        "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0300f120000bhaokemqv7m6s290p4i0",
        "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200fbc0000bd3jf6jcp23da5ss8tsg",
        "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
    )

    fun getDiffImage(array: Array<String> = IMAGE_SOURCE_ALL): String {
        val random = (Math.random() * array.size).toInt()
        return array[random]
    }

    fun getDiffImage(count: Int): MutableList<String> {
        val imageSet = mutableSetOf<String>()
        do {
            val image = getDiffImage()
            imageSet.add(image)
        } while (imageSet.size < count)
        return imageSet.toMutableList()
    }

    fun getDiffVideos(count: Int = 1): MutableList<String> {
        val videoSet = mutableSetOf<String>()
        do {
            val random = (Math.random() * VIDEO_SOURCE.size).toInt()
            val video = VIDEO_SOURCE[random]
            videoSet.add(video)
        } while (videoSet.size < count)
        return videoSet.toMutableList()
    }

    fun getLolZip(): String {
        return "https://vip.d0.baidupan.com/file/?UDYAPg4/VGUDClFpUWQBbVplVW1R5gaPUedQvwHGUNUC6lWDAMkOvVOeAchX5lDKV7MD4FWqAIVWcVC/BdlV21C2AM8O3VRHA3VRKlHtAYtasVW5UZUGgFG+UNcByVC2AplVnQC7DtBThQG/V4BQ7FfoA51VtADkVvFQ0QVvVWBQCABhDmdULQN7UX1RbAFwWiZVNlFnBmhRM1AJATRQNQI/VTQAaQ5uUz0BZFc3UGFXKwNgVXkAP1ZkUGIFYVVgUGQAZQ5kVCYDJVF3UTgBMlowVWFRNwYrUWZQZwFyUGECNVUtADgOPlM8ATVXM1BmV2wDZVVsAGZWMlBjBT9VY1AyAGEOYlQwA2JRP1EwATdaNVUzUTcGPFE1UGEBZVAyAjFVYQBzDiBTbAEnVyVQJFd+A2NVeABrVjVQawVrVWdQYgBuDmBUNANzUXNRbAFtWmVVNlEzBjVRY1BvAWVQYwI3VTMAbg5vUzABeVctUHdXawNqVX0AP1ZgUGMFYFVsUGEAYg5iVDYDZ1E1USMBdVpwVSdRMwY1UWNQYgFrUGkCNlUzAG4OYlM1AXFXdlA4V30DO1U4ADBWY1B4BWtVZVBiAHkOYVQzA21RKVE0ATZaNg=="
    }
}
