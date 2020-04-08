package com.june.imageabout.vo

import android.os.Parcel
import android.os.Parcelable

class ImageVo(
    val url: String,
    val thumbnail: String,
    val width: Int,
    val height: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(thumbnail)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageVo> {
        override fun createFromParcel(parcel: Parcel): ImageVo {
            return ImageVo(parcel)
        }

        override fun newArray(size: Int): Array<ImageVo?> {
            return arrayOfNulls(size)
        }
    }

}