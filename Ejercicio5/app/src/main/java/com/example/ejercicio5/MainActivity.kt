package com.example.ejercicio5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var boton: Button
    private lateinit var editText: EditText
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        boton = findViewById(R.id.botonSegundaActividad)
        editText = findViewById(R.id.editTextMessage)

        boton.setOnClickListener {
            val message = editText.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("EXTRA_MESSAGE", message)
            startActivity(intent)
        }


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                var currentTime = System.currentTimeMillis()
                currentTime /= 1000

                if (currentTime - backPressedTime < 2) {
                    finish()
                } else {
                    val txtExit = "Pulsa una vez más para cerrar la aplicación"
                    Toast.makeText(applicationContext, txtExit,
                        Toast.LENGTH_SHORT).show()
                    backPressedTime = currentTime
                }
            }
        })
    }
}
