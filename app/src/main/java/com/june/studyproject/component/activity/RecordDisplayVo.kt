package com.june.studyproject.component.activity

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class RecordDisplayVo(
    val title: String,
    val desc: String,
    val titleColor: Int = Color.parseColor("#333333"),
    val descColor: Int = Color.parseColor("#999999"),
    private val timeInMillis: Long = System.currentTimeMillis()
) : Parcelable {

    var hour: String
    var minute: String
    var second: String
    var millis: String

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong()
    ) {
        hour = parcel.readString() ?: ""
        minute = parcel.readString() ?: ""
        second = parcel.readString() ?: ""
        millis = parcel.readString() ?: ""
    }

    init {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        hour = String.format("%02d h", calendar.get(Calendar.HOUR_OF_DAY))
        minute = String.format("%02d m", calendar.get(Calendar.MINUTE))
        second = String.format("%02d s", calendar.get(Calendar.SECOND))
        millis = String.format("%03d ms", calendar.get(Calendar.MILLISECOND))
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(desc)
        parcel.writeInt(titleColor)
        parcel.writeInt(descColor)
        parcel.writeLong(timeInMillis)
        parcel.writeString(hour)
        parcel.writeString(minute)
        parcel.writeString(second)
        parcel.writeString(millis)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecordDisplayVo> {
        override fun createFromParcel(parcel: Parcel): RecordDisplayVo {
            return RecordDisplayVo(parcel)
        }

        override fun newArray(size: Int): Array<RecordDisplayVo?> {
            return arrayOfNulls(size)
        }
    }
}