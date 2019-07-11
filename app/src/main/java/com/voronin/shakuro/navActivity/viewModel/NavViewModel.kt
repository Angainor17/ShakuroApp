package com.voronin.shakuro.navActivity.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voronin.shakuro.navActivity.ScreenInfo

class NavViewModel : ViewModel() {

    val screenLiveData = MutableLiveData<ScreenInfo>()

    fun navigateScreen(screen: Int, params: Bundle? = null) {
        screenLiveData.postValue(ScreenInfo(screen, params))
    }
}