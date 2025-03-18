package com.example.ejercicio9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextBirthday = findViewById<EditText>(R.id.editTextBirthday)
        val buttonCreate = findViewById<Button>(R.id.buttonCreateContact)

        buttonCreate.setOnClickListener {
            val resultIntent = intent
            resultIntent.putExtra("contact_name", editTextName.text.toString())
            resultIntent.putExtra("contact_phone", editTextPhone.text.toString())
            resultIntent.putExtra("contact_email", editTextEmail.text.toString())
            resultIntent.putExtra("contact_birthday", editTextBirthday.text.toString())
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
