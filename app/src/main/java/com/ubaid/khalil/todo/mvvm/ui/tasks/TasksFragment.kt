package com.ubaid.khalil.todo.mvvm.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ubaid.khalil.todo.mvvm.R
import com.ubaid.khalil.todo.mvvm.databinding.FragmentTasksBinding

class TasksFragment : Fragment(R.layout.fragment_tasks) {
    private lateinit var binding: FragmentTasksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasksBinding.bind(view)
    }
}