package com.covid19.monitoring.utils

import androidx.test.espresso.IdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    fun getIdlingResource(): IdlingResource {
        return mCountingIdlingResource
    }
}