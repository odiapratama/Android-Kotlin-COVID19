package com.covid19.monitoring.views.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.covid19.monitoring.views.ui.maps.MapsFragment
import com.covid19.monitoring.views.ui.news.NewsFragment
import com.covid19.monitoring.views.ui.statistics.StatisticsFragment

class HomePagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NewsFragment()
            1 -> StatisticsFragment()
            else -> MapsFragment()
        }
    }

    override fun getCount() = 3
}