package com.example.practica5

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.practica5.databinding.ActivityTaskEditBinding

class TaskEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_edit)

        // Si se recibe una tarea, rellenamos los campos para editarla
        val task = intent.getSerializableExtra("task") as? Task
        val position = intent.getIntExtra("position", -1)
        if (task != null) {
            binding.etTitle.setText(task.title)
            binding.etDescription.setText(task.description)
            binding.cbCompleted.isChecked = task.completed
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val description = binding.etDescription.text.toString().trim()
            val completed = binding.cbCompleted.isChecked

            if (title.isEmpty()) {
                Toast.makeText(this, "El título es obligatorio", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val newTask = Task(title, description, completed)
            val resultIntent = intent
            resultIntent.putExtra("task", newTask)
            if (position != -1) {
                resultIntent.putExtra("position", position)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
