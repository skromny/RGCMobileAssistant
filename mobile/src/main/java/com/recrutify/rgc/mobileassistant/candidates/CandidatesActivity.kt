package com.recrutify.rgc.mobileassistant.candidates

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import com.recrutify.rgc.mobileassistant.BaseActivity
import com.recrutify.rgc.mobileassistant.R
import com.recrutify.rgc.mobileassistant.common.CustomPagerAdapter
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_single_page.*
import kotlinx.android.synthetic.main.app_bar_single_page.*
import kotlinx.android.synthetic.main.single_page_content.*
import javax.inject.Inject

class CandidatesActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var pagerAdapter: CustomPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_single_page)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setCheckedItem(R.id.nav_candidates)
        nav_view.setNavigationItemSelectedListener(this)


        pagerAdapter = CustomPagerAdapter(this.supportFragmentManager)

        pagerAdapter!!.addPage("List", CandidatesListFragment())

        view_pager.adapter = pagerAdapter
    }

    override fun onFragmentInteraction(uri: Uri) {
        super.onFragmentInteraction(uri)

        Log.d("CandidatesActivity", "onFragmentInteraction ${uri}")
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}