package com.example.practica5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskAdapter.OnTaskClickListener {

    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    // Registro para recibir el resultado de TaskEditActivity (para añadir o editar)
    private val taskEditLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newTask = result.data?.getSerializableExtra("task") as? Task
            val position = result.data?.getIntExtra("position", -1) ?: -1
            if (newTask != null) {
                if (position == -1) {
                    // Nueva tarea
                    taskViewModel.addTask(newTask)
                } else {
                    // Actualizar tarea existente
                    taskViewModel.updateTask(newTask, position)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configurar DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = taskViewModel

        // Configurar RecyclerView
        adapter = TaskAdapter(taskViewModel.tasks.value ?: listOf(), this)
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = adapter

        // Observar los cambios en la lista de tareas para actualizar el adaptador
        taskViewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        // FAB para lanzar la actividad para añadir una nueva tarea
        binding.fab.setOnClickListener {
            val intent = Intent(this, TaskEditActivity::class.java)
            taskEditLauncher.launch(intent)
        }
    }

    // Al pulsar en el título de una tarea, se lanza la actividad para editarla.
    override fun onTaskTitleClicked(task: Task, position: Int) {
        val intent = Intent(this, TaskEditActivity::class.java)
        intent.putExtra("task", task)
        intent.putExtra("position", position)
        taskEditLauncher.launch(intent)
    }

    // Al pulsar en la checkbox, se cambia el estado de la tarea.
    override fun onTaskCheckboxClicked(task: Task, position: Int) {
        taskViewModel.toggleTaskCompletion(position)
    }
}