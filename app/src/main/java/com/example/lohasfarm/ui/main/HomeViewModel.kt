package com.example.lohasfarm.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lohasfarm.ui.main.nav.Tabs

class HomeViewModel (application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _position = MutableLiveData(Tabs.FARM_PAGE)
    val position: LiveData<Tabs> = _position

    fun onPositionChanged(position: Tabs) {
        _position.value = position
    }

}