package com.covid19.monitoring.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun slideAnimation(view: View, show: Boolean) {
    if (show) {
        view.visibility = View.VISIBLE
        view.alpha = 0.0F
        view.animate()
            .translationY(view.height.toFloat())
            .alpha(1.0F)
            .translationX(0F).translationY(0F)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = View.VISIBLE
                    super.onAnimationEnd(animation)
                }
            }).duration = 500
    } else {
        view.animate()
            .alpha(0.0F)
            .translationX(0F).translationY(0F)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = View.GONE
                    super.onAnimationEnd(animation)
                }
            }).duration = 500
    }
}