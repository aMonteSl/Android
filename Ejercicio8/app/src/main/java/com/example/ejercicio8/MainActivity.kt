package com.example.ejercicio8

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonAddContact: Button
    private val contacts = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

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

    // Inicializa las vistas del layout
    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewContacts)
        editTextName = findViewById(R.id.editTextName)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonAddContact = findViewById(R.id.buttonAddContact)
    }

    // Configura el RecyclerView con su adaptador y LayoutManager
    private fun setupRecyclerView() {
        adapter = ContactAdapter(contacts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    // Configura el botón para añadir un nuevo contacto
    private fun setupButton() {
        buttonAddContact.setOnClickListener {
            addNewContact()
        }
    }

    // Recoge los datos de los campos de texto, valida y añade un nuevo contacto
    private fun addNewContact() {
        val name = editTextName.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()

        if (name.isNotEmpty() && phone.isNotEmpty()) {
            val newContact = Contact(name, phone)
            contacts.add(newContact)
            adapter.notifyItemInserted(contacts.size - 1)
            clearInputFields()
        } else {
            Toast.makeText(this, "Por favor, introduce nombre y teléfono", Toast.LENGTH_SHORT).show()
        }
    }

    // Limpia los campos de entrada tras añadir el contacto
    private fun clearInputFields() {
        editTextName.text.clear()
        editTextPhone.text.clear()
    }
}