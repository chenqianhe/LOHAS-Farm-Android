package com.example.lohasfarm.logic.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ActivityPageViewModel(application: Application): AndroidViewModel(application) {
    /**
     * true代表农副产品界面， false代表农场活动界面
     */
    private val _activityItemState = MutableLiveData(true)
    val activityItemState: LiveData<Boolean> = _activityItemState

}