package com.example.impl.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.impl.room.model.MovieModel

@Database(entities = [MovieModel::class], version = 1)
abstract class MoviesRoomDataBase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        private const val CONTACT_DATABASE = "movies.db"

        fun DaoInstance(context: Context): MoviesRoomDataBase {
            return Room.databaseBuilder(
                context,
                MoviesRoomDataBase::class.java,
                CONTACT_DATABASE
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}