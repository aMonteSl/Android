package com.example.practica5

import java.io.Serializable

data class Task(
    var title: String,
    var description: String,
    var completed: Boolean = false
) : Serializable
