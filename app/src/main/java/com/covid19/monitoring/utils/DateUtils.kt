package com.covid19.monitoring.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.formatTime(): String {
    val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return sdf.format(Date(this))
}