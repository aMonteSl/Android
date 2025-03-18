package com.example.ejercicio9

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetallesContactoActivity : AppCompatActivity() {

    private lateinit var textViewDetailName: TextView
    private lateinit var textViewDetailPhone: TextView
    private lateinit var textViewDetailEmail: TextView
    private lateinit var textViewDetailBirthday: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_contacto)

        initViews()
        displayContactDetails()
    }

    private fun initViews() {
        textViewDetailName = findViewById(R.id.textViewDetailName)
        textViewDetailPhone = findViewById(R.id.textViewDetailPhone)
        textViewDetailEmail = findViewById(R.id.textViewDetailEmail)
        textViewDetailBirthday = findViewById(R.id.textViewDetailBirthday)
    }

    private fun displayContactDetails() {
        val contact = intent.getSerializableExtra("contact") as? Contact
        if (contact != null) {
            textViewDetailName.text = "Nombre: ${contact.name}"
            textViewDetailPhone.text = "Teléfono: ${contact.phone}"
            textViewDetailEmail.text = "Email: ${contact.email}"
            textViewDetailBirthday.text = "Cumpleaños: ${contact.birthday}"
        } else {
            Toast.makeText(this, "No se encontraron detalles del contacto", Toast.LENGTH_SHORT).show()
        }
    }
}
