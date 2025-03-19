package com.example.basedatos1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pelicula::class], version = 1, exportSchema = false)
abstract class PeliculasRoomDatabase : RoomDatabase() {

    abstract fun peliculaDAO(): PeliculaDAO

    companion object {
        @Volatile
        private var INSTANCE: PeliculasRoomDatabase? = null

        fun getInstance(context: Context): PeliculasRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeliculasRoomDatabase::class.java,
                    "pelicula_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}