package com.covid19.monitoring.views.ui.news

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.covid19.monitoring.R
import com.covid19.monitoring.data.model.DailyUpdateData
import com.covid19.monitoring.data.model.GlobalData
import com.covid19.monitoring.views.ui.home.HomeActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsBehaviourTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<HomeActivity> =
        ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun globalDataDisplayedCheck() {
        onView(Matchers.allOf(withId(R.id.cvGlobalData))).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    @Test
    fun bindDailyUpdatesTest() {
        onData(CoreMatchers.`is`(CoreMatchers.instanceOf(DailyUpdateData::class.java)))
    }

    @Test
    fun bindGlobalDataTest() {
        onData(CoreMatchers.`is`(CoreMatchers.instanceOf(GlobalData::class.java)))
    }

    @Test
    fun itemDailyUpdatesDisplayedTest() {
        onView(withId(R.id.rvDailyUpdates)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
}