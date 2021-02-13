package com.covid19.monitoring.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.mockito.Mockito.mock

fun <T> LiveData<T>.observeTest(block: (Observer<T>) -> Unit) {
    val observer = mock(Observer<T> { }.javaClass)
    try {
        observeForever(observer)
        block(observer)
    } finally {
        removeObserver(observer)
    }
}