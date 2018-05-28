package com.recrutify.rgc.mobileassistant.projects

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.recrutify.rgc.mobileassistant.Model.Resource
import com.recrutify.rgc.mobileassistant.common.AbsentLiveData
import javax.inject.Inject

class GeneralProjectDetailViewModel @Inject constructor(projectsRepository: ProjectsRepository) : ViewModel(){
    val name = "ProjectsListViewModel"

    private val id = MutableLiveData<Int>()

    val results: LiveData<Resource<Project>> = Transformations
            .switchMap(id) { projectId ->
                    projectsRepository.getProject(projectId)
            }

    fun setProjectId(_id: Int) {
        id.value = _id
    }
}