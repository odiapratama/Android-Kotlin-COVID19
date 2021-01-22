package com.covid19.monitoring.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun <T> CoroutineScope.safeApiCall(
    call: suspend () -> T,
    error: (Throwable) -> Unit = {}
) {
    launch {
        try {
            call()
        } catch (t: Throwable) {
            error(t)
        }
    }
}

fun launchOn(coroutineContext: CoroutineContext, call: CoroutineScope.() -> (Unit)) {
    GlobalScope.launch(coroutineContext) {
        call(this)
    }
}