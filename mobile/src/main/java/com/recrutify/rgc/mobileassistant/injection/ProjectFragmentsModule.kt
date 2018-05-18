package com.recrutify.rgc.mobileassistant.injection

import com.recrutify.rgc.mobileassistant.projects.ProjectsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ProjectFragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeProjectListFragment(): ProjectsListFragment

}
