package com.example.ejercicio9

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ContactAdapter.OnContactClickListener {
    private val REQUEST_ADD_CONTACT = 1
    private lateinit var toolbar: MaterialToolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddContact: FloatingActionButton
    private val contacts = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerViewContacts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContactAdapter(contacts, this)
        recyclerView.adapter = adapter

        fabAddContact = findViewById(R.id.fab_add_contact)
        fabAddContact.setOnClickListener {
            val addContactIntent = Intent(this, CreateContactActivity::class.java)
            startActivityForResult(addContactIntent, REQUEST_ADD_CONTACT)
        }
    }

    override fun onContactClick(contact: Contact) {
        Toast.makeText(this, "Contacto seleccionado: ${contact.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ADD_CONTACT && resultCode == RESULT_OK) {
            data?.let {
                val name = it.getStringExtra("contact_name")
                val phone = it.getStringExtra("contact_phone")
                val email = it.getStringExtra("contact_email")
                val birthday = it.getStringExtra("contact_birthday")
                if (name != null && phone != null && email != null && birthday != null) {
                    val newContact = Contact(name, phone, email, birthday)
                    contacts.add(newContact)
                    adapter.notifyItemInserted(contacts.size - 1)
                }
            }
        }
    }
}
