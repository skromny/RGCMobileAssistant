package com.recrutify.rgc.mobileassistant.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.util.SparseIntArray
import com.recrutify.rgc.mobileassistant.projects.Project
import com.recrutify.rgc.mobileassistant.projects.ProjectsSearchResult
import java.util.*

@Dao
abstract class ProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(project: Project)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertList(projects: List<Project>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(projectsSearchResult: ProjectsSearchResult)


    @Query("SELECT * FROM ProjectsSearchResult WHERE `query` = :query")
    abstract fun search(query: String): LiveData<ProjectsSearchResult>

    fun loadOrdered(projectIds: List<Int>): LiveData<List<Project>> {
        val order = SparseIntArray()
        projectIds.withIndex().forEach {
            order.put(it.value, it.index)
        }
        return Transformations.map(loadById(projectIds)) { repositories ->
            Collections.sort(repositories) { r1, r2 ->
                val pos1 = order.get(r1.id)
                val pos2 = order.get(r2.id)
                pos1 - pos2
            }
            repositories
        }
    }

    @Query("SELECT * FROM Project WHERE id = :projectId")
    abstract fun get(projectId: Int): LiveData<Project>

    @Query("SELECT * FROM Project WHERE id in (:projectIds)")
    protected abstract fun loadById(projectIds: List<Int>): LiveData<List<Project>>
}