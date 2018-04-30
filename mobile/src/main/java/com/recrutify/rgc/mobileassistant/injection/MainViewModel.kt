package com.recrutify.rgc.mobileassistant.injection

import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    val name = "MainViewModel.ok"
}