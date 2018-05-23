package com.recrutify.rgc.mobileassistant.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.util.SparseIntArray
import com.recrutify.rgc.mobileassistant.candidates.Candidate
import com.recrutify.rgc.mobileassistant.candidates.CandidatesSearchResult
import java.util.*

@Dao
abstract class CandidatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(candidate: Candidate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertList(projects: List<Candidate>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(projectsSearchResult: CandidatesSearchResult)

    @Query("SELECT * FROM CandidatesSearchResult WHERE `query` = :query")
    abstract fun search(query: String): LiveData<CandidatesSearchResult>

    fun loadOrdered(candidateIds: List<Int>): LiveData<List<Candidate>> {
        val order = SparseIntArray()
        candidateIds.withIndex().forEach {
            order.put(it.value, it.index)
        }
        return Transformations.map(loadById(candidateIds)) { repositories ->
            Collections.sort(repositories) { r1, r2 ->
                val pos1 = order.get(r1.id)
                val pos2 = order.get(r2.id)
                pos1 - pos2
            }
            repositories
        }
    }

    @Query("SELECT * FROM Candidate WHERE id in (:candidateIds)")
    protected abstract fun loadById(candidateIds: List<Int>): LiveData<List<Candidate>>

}