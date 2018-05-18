package com.recrutify.rgc.mobileassistant.projects

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import com.recrutify.rgc.mobileassistant.BaseActivity
import com.recrutify.rgc.mobileassistant.R
import com.recrutify.rgc.mobileassistant.common.CustomPagerAdapter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_projects.*
import kotlinx.android.synthetic.main.app_bar_projects.*
import kotlinx.android.synthetic.main.content_projects.*
import javax.inject.Inject

class ProjectsActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var pagerAdapter: CustomPagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_projects)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setCheckedItem(R.id.nav_projects)
        nav_view.setNavigationItemSelectedListener(this)


        pagerAdapter = CustomPagerAdapter(this.supportFragmentManager)

        pagerAdapter!!.addPage("List", ProjectsListFragment())

        projects_view_pager.adapter = pagerAdapter
    }

    override fun onFragmentInteraction(uri: Uri) {
        super.onFragmentInteraction(uri)

        Log.d("ProjectsActivity", "onFragmentInteraction ${uri}")
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}
