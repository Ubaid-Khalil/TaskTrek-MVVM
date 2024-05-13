package com.ubaid.khalil.todo.mvvm.utils

import android.app.Activity
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.core.view.WindowCompat

fun Activity.setStatusBarColour(@ColorInt color: Int, isColorLight: Boolean) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color
    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
        isColorLight
}

fun Activity.setNavigationBarColour(@ColorInt color: Int, isColorLight: Boolean) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.navigationBarColor = color
    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
        isColorLight
}