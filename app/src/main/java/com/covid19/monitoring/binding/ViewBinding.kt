package com.covid19.monitoring.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.covid19.monitoring.R
import com.covid19.monitoring.utils.formatTime
import com.covid19.monitoring.utils.toNumberSeparator
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("bindPagerAdapter")
fun bindPagerAdapter(view: ViewPager, adapter: PagerAdapter) {
    view.adapter = adapter
    view.offscreenPageLimit = 3
}

@BindingAdapter("bindNavigation")
fun bindNavigation(view: ViewPager, nav: BottomNavigationView) {
    view.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) = Unit
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            nav.menu.getItem(position).isChecked = true
        }
    })

    nav.setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.item_news -> view.currentItem = 0
            R.id.item_statistics -> view.currentItem = 1
            R.id.item_maps -> view.currentItem = 2
        }
        true
    }
}

@BindingAdapter("setDateFormat")
fun TextView.setDateFormat(time: Long?) {
    this.text = time?.formatTime()
}