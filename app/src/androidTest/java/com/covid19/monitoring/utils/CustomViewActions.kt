package com.covid19.monitoring.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher


fun clickViewWithId(id: Int) = object : ViewAction {

    override fun getConstraints(): Matcher<View>? = null

    override fun getDescription() = "View clicked"

    override fun perform(uiController: UiController?, view: View?) {
        view?.findViewById<View>(id)?.performClick()
    }
}