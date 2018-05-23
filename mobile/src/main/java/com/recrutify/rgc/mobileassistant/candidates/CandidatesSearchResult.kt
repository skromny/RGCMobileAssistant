package com.recrutify.rgc.mobileassistant.candidates

import android.arch.persistence.room.Entity
import android.arch.persistence.room.TypeConverters
import com.recrutify.rgc.mobileassistant.db.CommonTypeConverters

@Entity(primaryKeys = ["query"])
@TypeConverters(CommonTypeConverters::class)
data class CandidatesSearchResult(
    val query: String,
    val candidateIds: List<Int>,
    val totalCount: Int,
    val next: Int
)
