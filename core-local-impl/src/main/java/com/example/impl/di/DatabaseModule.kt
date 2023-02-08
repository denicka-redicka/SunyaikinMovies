package com.example.impl.di

import android.app.Application
import com.example.impl.room.MoviesDao
import com.example.impl.room.MoviesRoomDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalDataSourceModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMoviesDataBase(application: Application): MoviesRoomDataBase{
        return MoviesRoomDataBase.DaoInstance(application)
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MoviesRoomDataBase): MoviesDao {
        return db.getMoviesDao()
    }
}