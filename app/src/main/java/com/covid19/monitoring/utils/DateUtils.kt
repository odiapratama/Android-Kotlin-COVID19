package com.covid19.monitoring.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.formatTime(): String {
    val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return sdf.format(Date(this))
}

fun String.convertDateFormat(): String =
    SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(
        SimpleDateFormat(DATE_FORMAT_SERVER, Locale.getDefault()).parse(this) ?: ""
    )

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    return SimpleDateFormat(DATE_FORMAT_SERVER, Locale.getDefault()).format(calendar.time)
}