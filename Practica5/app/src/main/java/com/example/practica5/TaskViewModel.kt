package com.example.practica5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val tasks: LiveData<MutableList<Task>>
        get() = _tasks

    fun addTask(task: Task) {
        _tasks.value?.add(task)
        // Forzamos la notificación de cambio
        _tasks.value = _tasks.value
    }

    fun updateTask(task: Task, position: Int) {
        _tasks.value?.set(position, task)
        _tasks.value = _tasks.value
    }

    fun toggleTaskCompletion(position: Int) {
        _tasks.value?.get(position)?.let {
            it.completed = !it.completed
        }
        _tasks.value = _tasks.value
    }
}
