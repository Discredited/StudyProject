package com.june.studyproject.common

object ConstHelper {

    val IMAGE_SOURCE = arrayOf(
        "http://www.17qq.com/img_qqtouxiang/44253845.jpeg",
        "http://www.17qq.com/img_qqtouxiang/75734077.jpeg",
        "http://www.17qq.com/img_qqtouxiang/82370370.jpeg",
        "http://www.17qq.com/img_qqtouxiang/75301766.jpeg",
        "http://www.17qq.com/img_qqtouxiang/74978283.jpeg",

        "http://www.17qq.com/img_qqtouxiang/89327395.jpeg",
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
        "http://a.hiphotos.baidu.com/exp/w=500/sign=7a6bb544fad3572c66e29cdcba126352/1b4c510fd9f9d72a10e3f2b0dc2a2834359bbb9c.jpg"
    )

    fun getDiffImage(): String {
        val random = (Math.random() * IMAGE_SOURCE.size).toInt()
        return IMAGE_SOURCE[random]
    }

    fun getDiffImage(count: Int): MutableList<String> {
        val imageSet = mutableSetOf<String>()
        do {
            val image = getDiffImage()
            imageSet.add(image)
        } while (imageSet.size < count)
        return imageSet.toMutableList()
    }
}
