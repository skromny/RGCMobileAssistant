package com.recrutify.rgc.mobileassistant.injection

import com.recrutify.rgc.mobileassistant.candidates.CandidatesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class CandidateFragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeCandidatesListFragment(): CandidatesListFragment

}