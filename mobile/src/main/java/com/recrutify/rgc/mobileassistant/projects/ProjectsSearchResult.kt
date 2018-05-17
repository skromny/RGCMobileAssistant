package com.recrutify.rgc.mobileassistant.projects

import android.arch.persistence.room.Entity
import android.arch.persistence.room.TypeConverters
import com.recrutify.rgc.mobileassistant.db.ProjectTypeConverters

@Entity(primaryKeys = ["query"])
@TypeConverters(ProjectTypeConverters::class)
data class ProjectsSearchResult(
    val query: String,
    val projectIds: List<Int>,
    val totalCount: Int,
    val next: Int?
)
