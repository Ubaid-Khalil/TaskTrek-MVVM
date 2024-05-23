package com.ubaid.khalil.todo.mvvm.tasktrek.utils

import android.app.Activity
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

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

fun Fragment.navigateToFragment(directions: NavDirections) {
    try {
        val navController = findNavController()
        when (val destination = navController.currentDestination) {
            is FragmentNavigator.Destination -> {
                if (javaClass.name == destination.className) {
                    navController.navigate(directions)
                }
            }

            is DialogFragmentNavigator.Destination -> {
                if (javaClass.name == destination.className) {
                    navController.navigate(directions)
                }
            }
        }
    } catch (e: Exception) {
        findNavController().navigate(directions)
    }
}