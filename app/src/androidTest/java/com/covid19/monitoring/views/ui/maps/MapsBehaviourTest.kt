package com.covid19.monitoring.views.ui.maps

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.covid19.monitoring.R
import com.covid19.monitoring.data.model.RegionData
import com.covid19.monitoring.views.ui.home.HomeActivity
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MapsBehaviourTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<HomeActivity> =
        ActivityScenarioRule(HomeActivity::class.java)

    @Before
    @Test
    fun initView() {
        Espresso.onView(ViewMatchers.withId(R.id.item_maps)).perform(
            ViewActions.click()
        )
    }

    @Test
    fun bindViewTest() {
        Espresso.onData(CoreMatchers.`is`(CoreMatchers.instanceOf(RegionData::class.java)))
    }
}