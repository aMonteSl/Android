package com.example.basedatos1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "pelicula_table")
data class Pelicula(
    @PrimaryKey(autoGenerate = true)
    val peliculaId: Int? = null,
    @ColumnInfo(name = "pelicula_titulo")
    val titulo: String,
    @ColumnInfo(name = "pelicula_anyo")
    val anyo: Int,
    val director: String
) : Serializable