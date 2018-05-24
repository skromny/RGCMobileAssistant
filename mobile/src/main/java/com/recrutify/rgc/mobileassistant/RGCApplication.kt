package com.recrutify.rgc.mobileassistant

import android.app.Activity
import android.app.Application
import android.databinding.DataBindingUtil
import com.recrutify.rgc.mobileassistant.injection.AppInjector
import com.recrutify.rgc.mobileassistant.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

class RGCApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        //setup Dagger-a
        //DaggerAppComponent.builder().application(this).build().inject(this)

        AppInjector.init(this)

        JodaTimeAndroid.init(this);
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}