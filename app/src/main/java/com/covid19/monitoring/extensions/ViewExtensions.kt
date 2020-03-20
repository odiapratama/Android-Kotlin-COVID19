package com.covid19.monitoring.extensions

import android.widget.TextView

fun TextView.setLeftDrawable(source: Int) =
    this.setCompoundDrawablesWithIntrinsicBounds(source, 0, 0, 0)