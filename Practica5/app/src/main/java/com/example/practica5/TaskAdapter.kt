package com.example.practica5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica5.databinding.ItemTaskBinding

class TaskAdapter(
    private var tasks: List<Task>,
    private val listener: OnTaskClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    interface OnTaskClickListener {
        fun onTaskTitleClicked(task: Task, position: Int)
        fun onTaskCheckboxClicked(task: Task, position: Int)
    }

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        with(holder.binding) {
            tvTaskTitle.text = task.title
            cbCompleted.isChecked = task.completed

            tvTaskTitle.setOnClickListener {
                listener.onTaskTitleClicked(task, position)
            }
            cbCompleted.setOnClickListener {
                listener.onTaskCheckboxClicked(task, position)
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
