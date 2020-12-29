package com.june.studyproject.component.fragment.lifecycle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.june.studyproject.component.activity.lifecycle.RecordDisplayVo

class FragmentLifecycleViewModel(application: Application) : AndroidViewModel(application) {

    val mRecordList = mutableListOf<RecordDisplayVo>()
    val mItemChangeLive: MutableLiveData<Boolean> = MutableLiveData()
}