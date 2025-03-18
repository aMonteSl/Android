package com.example.ejercicio9

import java.io.Serializable

data class Contact(
    val name: String,
    val phone: String,
    val email: String,
    val birthday: String
) : Serializable
