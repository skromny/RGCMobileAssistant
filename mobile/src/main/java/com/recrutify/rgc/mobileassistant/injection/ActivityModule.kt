package com.recrutify.rgc.mobileassistant.injection

import com.recrutify.rgc.mobileassistant.MainActivity
import com.recrutify.rgc.mobileassistant.candidates.CandidatesActivity
import com.recrutify.rgc.mobileassistant.login.LoginActivity
import com.recrutify.rgc.mobileassistant.projects.ProjectDetailActivity
import com.recrutify.rgc.mobileassistant.projects.ProjectsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [ProjectFragmentsModule::class])
    internal abstract fun contributeProjectsActivity(): ProjectsActivity

    @ContributesAndroidInjector(modules = [CandidateFragmentsModule::class])
    internal abstract fun contributeCandidatesActivity(): CandidatesActivity

    @ContributesAndroidInjector(modules = [ProjectFragmentsModule::class])
    internal abstract fun contributeProjectDetailActivity(): ProjectDetailActivity

}