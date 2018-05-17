package com.recrutify.rgc.mobileassistant.projects

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.recrutify.rgc.mobileassistant.AppExecutors
import com.recrutify.rgc.mobileassistant.R
import com.recrutify.rgc.mobileassistant.common.DataBoundListAdapter
import com.recrutify.rgc.mobileassistant.databinding.ProjectItemBinding

class ProjectsListAdapter(private val dataBindingComponent: DataBindingComponent,
                          appExecutors: AppExecutors,
                          //private val showFullName: Boolean,
                          private val projectClickCallback: ((Project) -> Unit)?
) : DataBoundListAdapter<Project, ProjectItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem.id == newItem.id
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ProjectItemBinding {
        val binding = DataBindingUtil.inflate<ProjectItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.project_item,
                parent,
                false,
                dataBindingComponent
        )
        //binding.showFullName = showFullName
        binding.root.setOnClickListener {
            binding.project?.let {
                projectClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ProjectItemBinding, item: Project) {
        binding.project = item
    }


}