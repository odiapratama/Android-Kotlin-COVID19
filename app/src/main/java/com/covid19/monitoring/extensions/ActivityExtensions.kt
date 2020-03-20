package com.covid19.monitoring.extensions

import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback

fun AppCompatActivity.applyExitMaterialTransform() {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementsUseOverlay = false
}