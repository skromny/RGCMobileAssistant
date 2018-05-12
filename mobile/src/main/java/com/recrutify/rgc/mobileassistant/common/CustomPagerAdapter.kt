package com.recrutify.rgc.mobileassistant.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class CustomPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    val pages = mutableListOf<Page>()

    fun addPage(title: String, fragment : Fragment) {
        pages.add(Page(title, fragment))
    }

    override fun getItem(position: Int): Fragment {
        return pages[position].fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}