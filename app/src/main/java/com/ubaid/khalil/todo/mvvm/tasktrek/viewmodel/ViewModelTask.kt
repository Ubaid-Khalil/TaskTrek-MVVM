package com.ubaid.khalil.todo.mvvm.tasktrek.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ubaid.khalil.todo.mvvm.tasktrek.data.DaoTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelTask @Inject constructor(private val daoTask: DaoTask) : ViewModel() {
    val tasks = daoTask.getTasks().asLiveData()
}