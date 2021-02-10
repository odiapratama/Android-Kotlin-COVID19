package com.covid19.monitoring.views.ui.statistics

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.covid19.monitoring.R
import com.covid19.monitoring.data.model.RegionData
import com.covid19.monitoring.utils.EspressoIdlingResource
import com.covid19.monitoring.utils.clickViewWithId
import com.covid19.monitoring.views.adapters.RegionAdapter
import com.covid19.monitoring.views.ui.home.HomeActivity
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StatisticsBehaviourTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<HomeActivity> =
        ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Before
    @Test
    fun initView() {
        onView(withId(R.id.item_statistics)).perform(
            ViewActions.click()
        )
    }

    @Test
    fun itemStatisticsDisplayedTest() {
        onView(withId(R.id.rvRegion)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    @Test
    fun bindViewTest() {
        onData(CoreMatchers.`is`(CoreMatchers.instanceOf(RegionData::class.java)))
    }

    @Test
    fun itemClickedTest() {
        onView(withId(R.id.rvRegion)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RegionAdapter.BindingHolder>(
                0, clickViewWithId(R.id.cvRegion)
            )
        )
    }
}