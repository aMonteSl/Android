package com.example.practica4

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateContactActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextBirthday: EditText
    private lateinit var buttonCreateContact: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)

        editTextName = findViewById(R.id.editTextNameCreate)
        editTextPhone = findViewById(R.id.editTextPhoneCreate)
        editTextEmail = findViewById(R.id.editTextEmailCreate)
        editTextBirthday = findViewById(R.id.editTextBirthdayCreate)
        buttonCreateContact = findViewById(R.id.buttonCreateContact)

        buttonCreateContact.setOnClickListener {
            createContact()
        }
    }

    private fun createContact() {
        val name = editTextName.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val birthday = editTextBirthday.text.toString().trim()

        if(name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && birthday.isNotEmpty()){
            val newContact = Contact(name, phone, email, birthday)
            val resultIntent = intent
            resultIntent.putExtra("new_contact", newContact)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}