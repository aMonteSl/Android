package com.example.ejercicio2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var buttonConvertir: Button
    private lateinit var textDinero: EditText
    private lateinit var textResultado: EditText
    private lateinit var radioGroupLeft: RadioGroup
    private lateinit var radioGroupRight: RadioGroup
    private lateinit var resultadoGroup: Group

    private val conversionRates = mapOf(
        "Euros" to mapOf("Dolares" to 1.1, "Libras" to 0.85),
        "Dolares" to mapOf("Euros" to 0.91, "Libras" to 0.77),
        "Libras" to mapOf("Euros" to 1.18, "Dolares" to 1.3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeViews()
        setupButtonClickListener()
    }

    private fun initializeViews() {
        buttonConvertir = findViewById(R.id.buttonConvertir)
        textDinero = findViewById(R.id.Importe)
        textResultado = findViewById(R.id.resultado)
        radioGroupLeft = findViewById(R.id.radioGroupLeft)
        radioGroupRight = findViewById(R.id.radioGroupRight)
        resultadoGroup = findViewById(R.id.groupResultado)

        resultadoGroup.visibility = View.GONE
    }

    private fun setupButtonClickListener() {
        buttonConvertir.setOnClickListener {
            val importe = getImporte()
            val monedaIzquierdaId = radioGroupLeft.checkedRadioButtonId
            val monedaDerechaId = radioGroupRight.checkedRadioButtonId

            if (monedaIzquierdaId != -1 && monedaDerechaId != -1) {
                val monedaIzquierda = getSelectedRadioButtonText(monedaIzquierdaId)
                val monedaDerecha = getSelectedRadioButtonText(monedaDerechaId)
                val tasaConversion = getTasaConversion(monedaIzquierda, monedaDerecha)
                val resultado = importe * tasaConversion
                displayResultado(resultado)

                Log.d("MainActivity", "Moneda izquierda: $monedaIzquierda")
                Log.d("MainActivity", "Moneda derecha: $monedaDerecha")
                Log.d("MainActivity", "Tasa de conversión: $tasaConversion")
            } else {
                Log.d("MainActivity", "No se ha seleccionado una moneda en uno de los grupos")
            }
        }
    }

    private fun getImporte(): Double {
        val importeString = textDinero.text.toString()
        return if (importeString.isEmpty()) 0.0 else importeString.toDouble()
    }

    private fun getSelectedRadioButtonText(radioButtonId: Int): String {
        val selectedRadioButton = findViewById<RadioButton>(radioButtonId)
        return selectedRadioButton.text.toString()
    }

    private fun getTasaConversion(monedaIzquierda: String, monedaDerecha: String): Double {
        return conversionRates[monedaIzquierda]?.get(monedaDerecha) ?: 1.0
    }

    private fun displayResultado(resultado: Double) {
        val resultadoString = String.format("%.2f", resultado)
        textResultado.setText(resultadoString)
        resultadoGroup.visibility = if (resultado == 0.0) View.GONE else View.VISIBLE
    }
}