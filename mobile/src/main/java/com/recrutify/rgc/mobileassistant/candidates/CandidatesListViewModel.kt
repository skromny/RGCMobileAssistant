package com.recrutify.rgc.mobileassistant.candidates

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.recrutify.rgc.mobileassistant.Model.Resource
import com.recrutify.rgc.mobileassistant.common.AbsentLiveData
import javax.inject.Inject

class CandidatesListViewModel @Inject constructor(candidatesRepository: CandidatesRepository) : ViewModel() {
    val name = "CandidatesListViewModel"

    private val query = MutableLiveData<String>()

    val results: LiveData<Resource<List<Candidate>>> = Transformations
            .switchMap(query) { search ->

                if(search.isNullOrBlank())
                    AbsentLiveData.create<Resource<List<Candidate>>>()
                else
                    candidatesRepository.search(search)
            }

    fun setQuery(_query: String) {
        query.value = _query
    }
}
