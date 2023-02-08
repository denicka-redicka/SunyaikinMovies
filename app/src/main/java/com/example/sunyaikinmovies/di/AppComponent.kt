package com.example.sunyaikinmovies.di

import com.example.favorites.di.FavoritesFeatureDependencies
import com.example.details.di.MovieDetailsFeatureDependencies
import com.example.feauture_movies_impl.di.MoviesFeatureDependencies
import com.example.sunyaikinmovies.MainActivity
import dagger.Component
import javax.inject.Singleton

@[Singleton Component(modules = [AppModule::class], dependencies = [AppInjector::class])]
interface AppComponent: MoviesFeatureDependencies, MovieDetailsFeatureDependencies,
    FavoritesFeatureDependencies {

    fun inject(activity: MainActivity)
}