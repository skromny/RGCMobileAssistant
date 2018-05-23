package com.recrutify.rgc.mobileassistant.candidates

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.recrutify.rgc.mobileassistant.AppExecutors
import com.recrutify.rgc.mobileassistant.Model.Resource
import com.recrutify.rgc.mobileassistant.common.AbsentLiveData
import com.recrutify.rgc.mobileassistant.common.ApiSuccessResponse
import com.recrutify.rgc.mobileassistant.common.NetworkBoundResource
import com.recrutify.rgc.mobileassistant.db.CandidatesDao
import com.recrutify.rgc.mobileassistant.db.LocalDB
import com.recrutify.rgc.mobileassistant.db.UserDao
import javax.inject.Inject

class CandidatesRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val candidatesService: CandidatesService,
        private val db: LocalDB,
        private val candidatesDao: CandidatesDao,
        private val userDao: UserDao
) {

    fun search(query: String): LiveData<Resource<List<Candidate>>> {
        return object : NetworkBoundResource<List<Candidate>, List<Candidate>>(appExecutors) {

            override fun saveCallResult(item: List<Candidate>) {
                val projectIds = item.map { it.id }

                val repoSearchResult = CandidatesSearchResult(
                        query = query,
                        candidateIds = projectIds,
                        totalCount = projectIds.count(),
                        next = 2
                )
                db.beginTransaction()
                try {
                    candidatesDao.insertList(item)
                    candidatesDao.insert(repoSearchResult)
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
            }

            override fun shouldFetch(data: List<Candidate>?) = true //data == null

            override fun loadFromDb(): LiveData<List<Candidate>> {
                return Transformations.switchMap(candidatesDao.search(query)) { searchData ->
                    if (searchData == null) {
                        AbsentLiveData.create()
                    } else {
                        candidatesDao.loadOrdered(searchData.candidateIds)
                    }
                }
            }

            override fun createCall() = Transformations.switchMap(userDao.getUser()) { user ->
                if(user != null) {
                    candidatesService.getAllCandidates(mapOf(Pair("authorization", user.token)), 1, listOf(), null)
                }
                else
                    AbsentLiveData.create()
            }

            override fun processResponse(response: ApiSuccessResponse<List<Candidate>>)
                    : List<Candidate> {

                val body = response.body

                return body
            }
        }.asLiveData()
    }
}