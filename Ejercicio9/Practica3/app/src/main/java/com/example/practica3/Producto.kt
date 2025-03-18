package com.example.practica3
import java.io.Serializable

data class Producto(val nombre: String, val precio: String, var cantidad: Int = 0): Serializable

