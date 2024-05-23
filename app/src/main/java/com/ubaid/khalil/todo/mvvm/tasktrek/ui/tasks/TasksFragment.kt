package com.ubaid.khalil.todo.mvvm.tasktrek.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ubaid.khalil.todo.mvvm.tasktrek.R
import com.ubaid.khalil.todo.mvvm.tasktrek.databinding.FragmentTasksBinding
import com.ubaid.khalil.todo.mvvm.tasktrek.viewmodel.ViewModelTask
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {
    private lateinit var binding: FragmentTasksBinding

    private val viewModelTask: ViewModelTask by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasksBinding.bind(view)

        binding.apply {
            rvTasks.apply {
                viewModelTask.tasks.observe(viewLifecycleOwner) {

                }
            }
        }
    }
}