package com.recrutify.rgc.mobileassistant.projects

import android.databinding.DataBindingComponent
import android.support.v4.app.Fragment

class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters() = adapter
}
