package com.example.ejercicio7

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonAdd: Button
    private val data = mutableListOf<String>()
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupRecyclerView()
        setupButton()

    }

    // Inicializa las vistas utilizando findViewById
    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        buttonAdd = findViewById(R.id.buttonAdd)
    }

    // Configura el RecyclerView con su adaptador y LayoutManager
    private fun setupRecyclerView() {
        adapter = MyAdapter(data)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    // Configura el botón y define su acción al pulsarlo
    private fun setupButton() {
        buttonAdd.setOnClickListener {
            addNewElement()
        }
    }

    // Añade un nuevo elemento a la lista y notifica la inserción al adaptador
    private fun addNewElement() {
        val newElement = "Elemento ${data.size + 1}".uppercase()
        data.add(newElement)
        adapter.notifyItemInserted(data.size - 1)
    }

}