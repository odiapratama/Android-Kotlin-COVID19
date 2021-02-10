package com.covid19.monitoring.views.ui.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.covid19.monitoring.R
import com.covid19.monitoring.utils.clickViewWithId
import com.covid19.monitoring.views.adapters.RegionAdapter
import com.covid19.monitoring.views.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailBehaviourTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<HomeActivity> =
        ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun dataDetailDisplayedTest() {
        onView(withId(R.id.item_statistics)).perform(
            ViewActions.click()
        )
        onView(withId(R.id.rvRegion)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RegionAdapter.BindingHolder>(
                0, clickViewWithId(R.id.cvRegion)
            )
        )
        /*onView(
            Matchers.allOf(withId(R.id.cvData))).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )*/
    }
}