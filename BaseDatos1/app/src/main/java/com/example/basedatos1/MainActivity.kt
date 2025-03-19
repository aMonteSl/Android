package com.example.basedatos1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basedatos1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var database: PeliculasRoomDatabase
    private lateinit var dao: PeliculaDAO
    private lateinit var adapter: PeliculaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        database = PeliculasRoomDatabase.getInstance(applicationContext)
        dao = database.peliculaDAO()

        adapter = PeliculaAdapter(listOf())
        binding.rvPeliculas.layoutManager = LinearLayoutManager(this)
        binding.rvPeliculas.adapter = adapter

        dao.getAll().observe(this, Observer { peliculas ->
            adapter.updatePeliculas(peliculas)
        })

        binding.btnAddMovie.setOnClickListener {
            val nuevaPelicula = Pelicula(
                peliculaId = null,
                titulo = "La Gran Aventura",
                anyo = 2020,
                director = "Juan Pérez"
            )
            dao.insert(nuevaPelicula)
        }
    }
}