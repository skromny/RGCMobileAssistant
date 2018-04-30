package com.recrutify.rgc.mobileassistant.candidates

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import com.recrutify.rgc.mobileassistant.BaseActivity
import com.recrutify.rgc.mobileassistant.R
import kotlinx.android.synthetic.main.activity_candidates.*
import kotlinx.android.synthetic.main.app_bar_projects.*

class CandidatesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidates)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setCheckedItem(R.id.nav_candidates)
        nav_view.setNavigationItemSelectedListener(this)

    }
}
