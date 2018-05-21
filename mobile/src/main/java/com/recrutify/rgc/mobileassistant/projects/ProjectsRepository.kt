package com.recrutify.rgc.mobileassistant.projects

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.recrutify.rgc.mobileassistant.AppExecutors
import com.recrutify.rgc.mobileassistant.Model.Resource
import com.recrutify.rgc.mobileassistant.common.AbsentLiveData
import com.recrutify.rgc.mobileassistant.common.ApiResponse
import com.recrutify.rgc.mobileassistant.common.ApiSuccessResponse
import com.recrutify.rgc.mobileassistant.common.NetworkBoundResource
import com.recrutify.rgc.mobileassistant.db.LocalDB
import com.recrutify.rgc.mobileassistant.db.ProjectsDao
import com.recrutify.rgc.mobileassistant.db.UserDao
import javax.inject.Inject

class ProjectsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val projectsService: ProjectsService,
    private val db: LocalDB,
    private val projectsDao: ProjectsDao,
    private val userDao: UserDao) {



    fun search(query: String): LiveData<Resource<List<Project>>> {
        return object : NetworkBoundResource<List<Project>, List<Project>>(appExecutors) {

            override fun saveCallResult(item: List<Project>) {
                val projectIds = item.map { it.id }

                val repoSearchResult = ProjectsSearchResult(
                        query = query,
                        projectIds = projectIds,
                        totalCount = projectIds.count(),
                        next = 2
                )
                db.beginTransaction()
                try {
                    projectsDao.insertList(item)
                    projectsDao.insert(repoSearchResult)
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
            }

            override fun shouldFetch(data: List<Project>?) = true //data == null

            override fun loadFromDb(): LiveData<List<Project>> {
                return Transformations.switchMap(projectsDao.search(query)) { searchData ->
                    if (searchData == null) {
                        AbsentLiveData.create()
                    } else {
                        projectsDao.loadOrdered(searchData.projectIds)
                    }
                }
            }

            override fun createCall() = Transformations.switchMap(userDao.getUser()) { user ->
                    if(user != null) {
                        projectsService.getAllProjects(mapOf(Pair("authorization", user.token)), 1, listOf(), null)
                    }
                    else
                        AbsentLiveData.create()
                }

            override fun processResponse(response: ApiSuccessResponse<List<Project>>)
                    : List<Project> {

                val body = response.body

                return body
            }
        }.asLiveData()
    }
}