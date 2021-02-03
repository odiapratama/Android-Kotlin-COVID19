package com.covid19.monitoring.services

import kotlin.coroutines.CoroutineContext

open class CoroutineContextProviders(
    val io: CoroutineContext
)