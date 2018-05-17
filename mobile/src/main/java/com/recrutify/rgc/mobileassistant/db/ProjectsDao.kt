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

//    @Query("SELECT * FROM LoggedUser LIMIT 1")
//    fun getUser() : LiveData<LoggedUser>

    @Query("DELETE FROM LoggedUser")
    abstract fun removeLoggedUser()

    fun loadOrdered(repoIds: List<Int>): LiveData<List<Project>> {
        val order = SparseIntArray()
        repoIds.withIndex().forEach {
            order.put(it.value, it.index)
        }
        return Transformations.map(loadById(repoIds)) { repositories ->
            Collections.sort(repositories) { r1, r2 ->
                val pos1 = order.get(r1.id)
                val pos2 = order.get(r2.id)
                pos1 - pos2
            }
            repositories
        }
    }

    @Query("SELECT * FROM Project WHERE id in (:repoIds)")
    protected abstract fun loadById(repoIds: List<Int>): LiveData<List<Project>>

}