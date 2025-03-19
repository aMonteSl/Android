package com.example.basedatos1

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PeliculaDAO {

    @Insert
    fun insert(vararg pelicula: Pelicula)

    @Update
    fun update(pelicula: Pelicula)

    @Delete
    fun delete(pelicula: Pelicula)

    @Query("SELECT * FROM pelicula_table")
    fun getAll(): LiveData<List<Pelicula>>
}