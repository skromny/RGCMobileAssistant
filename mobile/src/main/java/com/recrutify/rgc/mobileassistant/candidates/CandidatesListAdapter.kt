package com.recrutify.rgc.mobileassistant.candidates

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.recrutify.rgc.mobileassistant.AppExecutors
import com.recrutify.rgc.mobileassistant.R
import com.recrutify.rgc.mobileassistant.common.DataBoundListAdapter
import com.recrutify.rgc.mobileassistant.databinding.CandidateItemBinding

class CandidatesListAdapter(private val dataBindingComponent: DataBindingComponent,
                            appExecutors: AppExecutors,
                            //private val showFullName: Boolean,
                            private val projectClickCallback: ((Candidate) -> Unit)?
) : DataBoundListAdapter<Candidate, CandidateItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Candidate>() {
            override fun areItemsTheSame(oldItem: Candidate, newItem: Candidate): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Candidate, newItem: Candidate): Boolean {
                return oldItem.id == newItem.id
            }
        }
) {

    override fun createBinding(parent: ViewGroup): CandidateItemBinding {
        val binding = DataBindingUtil.inflate<CandidateItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.candidate_item,
                parent,
                false,
                dataBindingComponent
        )
        //binding.showFullName = showFullName
        binding.root.setOnClickListener {
            binding.candidate?.let {
                projectClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: CandidateItemBinding, item: Candidate) {
        binding.candidate = item
    }


}