package com.ubaid.khalil.todo.mvvm.tasktrek.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ubaid.khalil.todo.mvvm.tasktrek.R
import com.ubaid.khalil.todo.mvvm.tasktrek.databinding.FragmentSplashBinding
import com.ubaid.khalil.todo.mvvm.tasktrek.utils.navigateToFragment
import com.ubaid.khalil.todo.mvvm.tasktrek.utils.setNavigationBarColour
import com.ubaid.khalil.todo.mvvm.tasktrek.utils.setStatusBarColour
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding

    private lateinit var activity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity)
            activity = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        setupViews()
        startTransition()
    }

    private fun setupViews() {
        activity.apply {
            setStatusBarColour(
                color = ContextCompat.getColor(this, R.color.background),
                isColorLight = true
            )
            setNavigationBarColour(
                color = ContextCompat.getColor(this, R.color.background),
                isColorLight = true
            )
        }
    }

    private fun startTransition() {
        lifecycleScope.launch {
            delay(3000)
            val direction = SplashFragmentDirections.actionSplashFragmentToTasksFragment()
            navigateToFragment(direction)
        }
    }
}