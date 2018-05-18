package com.recrutify.rgc.mobileassistant.projects

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.recrutify.rgc.mobileassistant.Model.Resource
import com.recrutify.rgc.mobileassistant.common.AbsentLiveData
import javax.inject.Inject

class ProjectsListViewModel @Inject constructor(projectsRepository: ProjectsRepository) : ViewModel() {
    val name = "ProjectsListViewModel"

    private val query = MutableLiveData<String>()

    val results: LiveData<Resource<List<Project>>> = Transformations
            .switchMap(query) { search ->

                if(search.isNullOrBlank())
                    AbsentLiveData.create<Resource<List<Project>>>()
                else
                    projectsRepository.search(search)
            }

    fun setQuery(_query: String) {
        query.value = _query
    }

}