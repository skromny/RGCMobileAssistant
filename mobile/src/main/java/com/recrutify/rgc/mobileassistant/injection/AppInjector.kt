package com.recrutify.rgc.mobileassistant.injection

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.recrutify.rgc.mobileassistant.RGCApplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {

    fun init(rgcApp: RGCApplication) {
        DaggerAppComponent.builder().application(rgcApp)
                .build().inject(rgcApp)

        rgcApp.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity?) {

            }

            override fun onActivityStarted(activity: Activity?) {

            }

            override fun onActivityDestroyed(activity: Activity?) {

            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityStopped(activity: Activity?) {

            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                handleActivityCreation(activity!!)
            }
        })
    }

    private fun handleActivityCreation(activity: Activity) {
        if(activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if(activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
                            super.onFragmentCreated(fm, f, savedInstanceState)
                            if(f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }

                    }, true)
        }
    }
}


