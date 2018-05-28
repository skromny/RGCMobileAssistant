package com.recrutify.rgc.mobileassistant.common

import android.databinding.DataBindingComponent
import android.support.v4.app.Fragment
import com.recrutify.rgc.mobileassistant.candidates.CandidateBindingAdapters
import com.recrutify.rgc.mobileassistant.projects.ProjectBindingAdapters

class FragmentDataBindingComponent(fragment: Fragment, labelsAdapter: LabelsAdapter) : DataBindingComponent {

    private val candidateBindingadapters = CandidateBindingAdapters(fragment)
    private val projectBindingadapters = ProjectBindingAdapters(fragment)
    private val commonBindingAdapters = CommonBindingAdapters(fragment, labelsAdapter)

    override fun getCandidateBindingAdapters() = candidateBindingadapters

    override fun getCommonBindingAdapters() = commonBindingAdapters

    override fun getProjectBindingAdapters() = projectBindingadapters
}