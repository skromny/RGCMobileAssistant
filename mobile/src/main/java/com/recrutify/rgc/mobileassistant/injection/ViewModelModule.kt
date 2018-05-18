package com.recrutify.rgc.mobileassistant.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.recrutify.rgc.mobileassistant.MainViewModel
import com.recrutify.rgc.mobileassistant.login.LoginViewModel
import com.recrutify.rgc.mobileassistant.projects.ProjectsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel : LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel : MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectsListViewModel::class)
    abstract fun bindProjectListViewModel(viewModel : ProjectsListViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}