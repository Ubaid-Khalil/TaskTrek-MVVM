package com.ubaid.khalil.todo.mvvm.tasktrek.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.ubaid.khalil.todo.mvvm.tasktrek.R
import com.ubaid.khalil.todo.mvvm.tasktrek.databinding.ActivityMainBinding
import com.ubaid.khalil.todo.mvvm.tasktrek.viewmodel.ViewModelTask
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModelTask: ViewModelTask by lazy {
        ViewModelProvider(this)[ViewModelTask::class.java]
    }
    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_nav_host) as NavHostFragment
        navHostFragment.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViews()
    }

    private fun setupViews() {
        viewModelTask.statusBarVisibility.observe(this) { isVisible ->
            binding.tbMain.isVisible = isVisible
        }
        setSupportActionBar(binding.tbMain)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.tasksFragment), null) {
            navController.navigateUp()
            true
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() or super.onSupportNavigateUp()
    }
}