package com.ubaid.khalil.todo.mvvm.tasktrek.ui.tasks

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaid.khalil.todo.mvvm.tasktrek.R
import com.ubaid.khalil.todo.mvvm.tasktrek.databinding.FragmentTasksBinding
import com.ubaid.khalil.todo.mvvm.tasktrek.ui.tasks.adapter.AdapterTask
import com.ubaid.khalil.todo.mvvm.tasktrek.utils.setNavigationBarColour
import com.ubaid.khalil.todo.mvvm.tasktrek.utils.setStatusBarColour
import com.ubaid.khalil.todo.mvvm.tasktrek.viewmodel.ViewModelTask
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {
    private lateinit var binding: FragmentTasksBinding

    private lateinit var activity: AppCompatActivity
    private val viewModelTask: ViewModelTask by lazy {
        ViewModelProvider(activity)[ViewModelTask::class.java]
    }
    private val adapterTask: AdapterTask by lazy { AdapterTask() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity)
            activity = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasksBinding.bind(view)

        setupViews()
        getTasks()
    }

    private fun setupViews() {
        activity.apply {
            setStatusBarColour(
                color = ContextCompat.getColor(this, R.color.on_surface),
                isColorLight = false
            )
            setNavigationBarColour(
                color = ContextCompat.getColor(this, R.color.background),
                isColorLight = true
            )
        }
        binding.apply {
            rvTasks.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = adapterTask
            }
        }
        viewModelTask.setStatusBarVisibility(true)
    }

    private fun getTasks() {
        viewModelTask.tasks.observe(viewLifecycleOwner) { tasks ->
            binding.apply {
                llcLoading.isVisible = false
                llcEmpty.isVisible = tasks.isEmpty()
            }
            adapterTask.submitList(tasks)
        }
    }
}