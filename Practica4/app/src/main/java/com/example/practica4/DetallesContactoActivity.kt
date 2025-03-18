package com.example.practica4

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetallesContactoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_contacto)

        val textViewName: TextView = findViewById(R.id.textViewDetailName)
        val textViewPhone: TextView = findViewById(R.id.textViewDetailPhone)
        val textViewEmail: TextView = findViewById(R.id.textViewDetailEmail)
        val textViewBirthday: TextView = findViewById(R.id.textViewDetailBirthday)

        // Recupera el contacto pasado por el Intent
        val contact = intent.getSerializableExtra("contact") as? Contact
        if (contact != null) {
            textViewName.text = "Nombre: ${contact.name}"
            textViewPhone.text = "Teléfono: ${contact.phone}"
            textViewEmail.text = "Email: ${contact.email}"
            textViewBirthday.text = "Cumpleaños: ${contact.birthday}"
        } else {
            textViewName.text = "No se pudo cargar el contacto"
        }
    }
}