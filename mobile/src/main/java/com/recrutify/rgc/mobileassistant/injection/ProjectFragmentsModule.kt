package com.recrutify.rgc.mobileassistant.injection

import com.recrutify.rgc.mobileassistant.projects.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ProjectFragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeProjectsListFragment(): ProjectsListFragment

    @ContributesAndroidInjector
    abstract fun contributeGeneralProjectDetailFragment(): GeneralProjectDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeCandidatesProjectDetailFragment(): CandidatesProjectDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeRecruitmentProjectDetailFragment(): RecruitmentProjectDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeActivityProjectDetailFragment(): ActivityProjectDetailFragment

}
