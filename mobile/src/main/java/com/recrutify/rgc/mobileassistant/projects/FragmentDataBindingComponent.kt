package com.recrutify.rgc.mobileassistant.projects

import android.databinding.DataBindingComponent
import android.support.v4.app.Fragment
import com.recrutify.rgc.mobileassistant.common.CommonBindingAdapters

class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {

    private val projectBindingadapters = FragmentBindingAdapters(fragment)
    private val commonBindingAdapters = CommonBindingAdapters(fragment)

    override fun getCommonBindingAdapters() = commonBindingAdapters

    override fun getFragmentBindingAdapters() = projectBindingadapters
}
