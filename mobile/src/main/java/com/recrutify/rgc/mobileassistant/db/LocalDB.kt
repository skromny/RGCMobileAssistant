package com.recrutify.rgc.mobileassistant.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.recrutify.rgc.mobileassistant.Model.Label
import com.recrutify.rgc.mobileassistant.candidates.Candidate
import com.recrutify.rgc.mobileassistant.candidates.CandidatesSearchResult
import com.recrutify.rgc.mobileassistant.login.LoggedUser
import com.recrutify.rgc.mobileassistant.projects.Project
import com.recrutify.rgc.mobileassistant.projects.ProjectsSearchResult

@Database(
    entities = [
        LoggedUser::class,
        Label::class,
        Project::class,
        Candidate::class,
        ProjectsSearchResult::class,
        CandidatesSearchResult::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDB : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun projectsDao() : ProjectsDao
    abstract fun candidatesDao() : CandidatesDao
}

