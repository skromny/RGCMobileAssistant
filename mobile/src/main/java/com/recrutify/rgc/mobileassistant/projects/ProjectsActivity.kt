package com.recrutify.rgc.mobileassistant.projects

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.recrutify.rgc.mobileassistant.BaseActivity
import com.recrutify.rgc.mobileassistant.BaseActivity_MembersInjector
import kotlinx.android.synthetic.main.activity_projects.*
import kotlinx.android.synthetic.main.app_bar_projects.*
import com.recrutify.rgc.mobileassistant.R
import dagger.android.AndroidInjection

class ProjectsActivity : BaseActivity() {

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
    }

}
