package com.covid19.monitoring.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.covid19.monitoring.R
import com.covid19.monitoring.extensions.loadImageCircleCrop
import com.covid19.monitoring.extensions.toGone
import com.covid19.monitoring.extensions.toVisible
import com.covid19.monitoring.services.Status
import com.covid19.monitoring.utils.convertDateFormat
import com.facebook.shimmer.ShimmerFrameLayout
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
fun TextView.setDateFormat(time: String?) {
    this.text = time?.convertDateFormat()
}

@BindingAdapter("setImage")
fun ImageView.setImage(isoCode: String) {
    this.loadImageCircleCrop("https://www.countryflags.io/$isoCode/shiny/64.png")
}

@BindingAdapter("setShimmerLoading")
fun ShimmerFrameLayout.setShimmerLoading(status: Status) {
    with(this) {
        when (status) {
            Status.LOADING -> {
                this.startShimmer()
                this.toVisible()
            }
            else -> {
                this.stopShimmer()
                this.toGone()
            }
        }
    }
}