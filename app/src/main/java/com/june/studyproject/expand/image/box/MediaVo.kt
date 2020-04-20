package com.june.studyproject.expand.image.box

import android.os.Parcel
import android.os.Parcelable

class MediaVo(
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

    companion object CREATOR : Parcelable.Creator<MediaVo> {
        override fun createFromParcel(parcel: Parcel): MediaVo {
            return MediaVo(parcel)
        }

        override fun newArray(size: Int): Array<MediaVo?> {
            return arrayOfNulls(size)
        }
    }
}