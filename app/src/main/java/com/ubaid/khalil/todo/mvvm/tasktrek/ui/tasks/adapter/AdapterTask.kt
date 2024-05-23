package com.ubaid.khalil.todo.mvvm.tasktrek.ui.tasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ubaid.khalil.todo.mvvm.tasktrek.data.Task
import com.ubaid.khalil.todo.mvvm.tasktrek.databinding.ItemTaskBinding

class AdapterTask : ListAdapter<Task, AdapterTask.ViewHolderTask>(TaskComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTask {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderTask(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderTask, position: Int) {
        holder.bindTask(getItem(position))
    }

    class ViewHolderTask(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTask(task: Task) {
            binding.apply {
                accbComplete.isChecked = task.isCompleted
                actvTitle.paint.isStrikeThruText = task.isCompleted
                actvTitle.text = task.title
                actvDescription.isVisible = task.description.isNotBlank()
                actvDescription.text = task.description
                acivImportant.isVisible = task.isImportant
                actvDateTime.text = task.dateFormatted
            }
        }
    }

    class TaskComparator() : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    }
}