package com.recrutify.rgc.mobileassistant

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.recrutify.rgc.mobileassistant.candidates.CandidatesActivity
import com.recrutify.rgc.mobileassistant.common.OnFragmentInteractionListener
import com.recrutify.rgc.mobileassistant.injection.ViewModelFactory
import com.recrutify.rgc.mobileassistant.login.LoggedUser
import com.recrutify.rgc.mobileassistant.login.LoginActivity
import com.recrutify.rgc.mobileassistant.login.UserRepository
import com.recrutify.rgc.mobileassistant.projects.ProjectsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


/**
 * Created by arturnowak on 12.02.2018.
 */
open class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var userRepository: UserRepository

    val drawerLayout : DrawerLayout? = null
    val actionBarDrawerToggle : ActionBarDrawerToggle? = null
    //val toolbar : Toolbar? = null

    lateinit var loggedUser : LoggedUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Dagger
        AndroidInjection.inject(this)

        userRepository.getLoggedUser().observe(this, Observer { user ->

            if (user == null) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                finish()
                startActivity(intent)
            }
            else
                loggedUser = user
        })

//        val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
//
//        val email = sharedPreferences.getString("email", null)
//        val password = sharedPreferences.getString("password", null)
//
//        if(email == null || password == null) {
//        }

//        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//
//        val toggle = ActionBarDrawerToggle(
//                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()

//        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.d("BaseActivity", "onFragmentInteraction ${uri}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //if jako wyrażenie (expression)
        return if (item.itemId == R.id.action_settings) true else super.onOptionsItemSelected(item)
        //lub tak:
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//
        drawer_layout.closeDrawer(GravityCompat.START)
//
        when (item.itemId) {
            R.id.nav_projects -> {
                val intent = Intent(getApplicationContext(), ProjectsActivity::class.java)
                startActivity(intent);
            }
            R.id.nav_candidates -> {
                val intent = Intent(getApplicationContext(), CandidatesActivity::class.java)
                startActivity(intent);
            }
//            R.id.nav_company -> {
//
//            }
//            R.id.nav_contacts -> {
//
//            }
//            R.id.nav_tasks -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
        }
//
        return true
    }

}