package com.recrutify.rgc.mobileassistant.projects

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import com.recrutify.rgc.mobileassistant.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import com.recrutify.rgc.mobileassistant.BaseActivity
import com.recrutify.rgc.mobileassistant.common.CustomPagerAdapter
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_pager_view.*
import kotlinx.android.synthetic.main.app_bar_pagerview.*
import kotlinx.android.synthetic.main.pageviewer_content.*
import javax.inject.Inject

class ProjectDetailActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var projectId : Int = -1
    var pagerAdapter: CustomPagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.extras?.let {
            projectId = it.getInt("PROJECT_ID")
        }

        setContentView(R.layout.activity_pager_view)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setCheckedItem(R.id.nav_projects)
        nav_view.setNavigationItemSelectedListener(this)

        pagerAdapter = CustomPagerAdapter(this.supportFragmentManager)

        pagerAdapter!!.addPage("Informacje", GeneralProjectDetailFragment.newInstance(projectId))
        pagerAdapter!!.addPage("Kandydaci", CandidatesProjectDetailFragment())
        pagerAdapter!!.addPage("Rekrutacja", RecruitmentProjectDetailFragment())
        pagerAdapter!!.addPage("Aktywności", ActivityProjectDetailFragment())

        view_pager.adapter = pagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onFragmentInteraction(uri: Uri) {
        super.onFragmentInteraction(uri)

        Log.d("ProjectsActivity", "onFragmentInteraction ${uri}")
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}

