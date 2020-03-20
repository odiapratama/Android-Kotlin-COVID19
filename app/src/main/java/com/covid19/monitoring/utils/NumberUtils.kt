package com.covid19.monitoring.utils

import java.text.NumberFormat
import java.util.*

fun Int.toNumberSeparator() = NumberFormat.getNumberInstance(Locale.getDefault()).format(this)