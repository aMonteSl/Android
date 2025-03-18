package com.example.practica1

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var botonSuma: Button
    private lateinit var botonResta: Button
    private lateinit var miTexto: TextView
    private lateinit var textInfo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        botonSuma = findViewById(R.id.botonSuma)
        botonResta = findViewById(R.id.botonRestar)
        miTexto = findViewById(R.id.textView)
        textInfo = findViewById(R.id.textView2)

        var contador = 0


        miTexto.text = "Bienvenido al sumador/restador"
        textInfo.text = ""

        botonSuma.setOnClickListener {
            contador++
            miTexto.text = "Numero pulsaciones: $contador"
            textInfo.text = "Sumando"

        }
        botonResta.setOnClickListener {
            contador--
            miTexto.text = "Numero pulsaciones: $contador"
            textInfo.text = "Restando"
        }
    }
}