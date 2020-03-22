package com.covid19.monitoring.extensions

import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.covid19.monitoring.R

fun TextView.setLeftDrawable(source: Int) =
    this.setCompoundDrawablesWithIntrinsicBounds(source, 0, 0, 0)

fun ImageView.loadImageCircleCrop(url: String) {
    load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_flag)
        error(R.drawable.ic_flag)
        transformations(CircleCropTransformation())
    }
}
