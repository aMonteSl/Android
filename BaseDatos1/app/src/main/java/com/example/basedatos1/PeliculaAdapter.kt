package com.example.basedatos1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter(private var peliculas: List<Pelicula>) :
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvAnyo: TextView = itemView.findViewById(R.id.tvAnyo)
        val tvDirector: TextView = itemView.findViewById(R.id.tvDirector)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = peliculas[position]
        holder.tvTitulo.text = pelicula.titulo
        // Asigna el valor del año en lugar del texto literal "Año"
        holder.tvAnyo.text = pelicula.anyo.toString()
        holder.tvDirector.text = pelicula.director
    }

    override fun getItemCount(): Int = peliculas.size

    fun updatePeliculas(newPeliculas: List<Pelicula>) {
        peliculas = newPeliculas
        notifyDataSetChanged()
    }
}