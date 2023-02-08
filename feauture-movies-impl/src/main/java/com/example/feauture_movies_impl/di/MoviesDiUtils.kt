package com.example.feauture_movies_impl.di

import android.app.Application
import android.content.Context

interface MoviesFeatureDependenciesProvider {
    val dependencies: MoviesFeatureDependencies
}

val Context.moviesDependenciesProvider: MoviesFeatureDependenciesProvider
    get() = when(this) {
        is MoviesFeatureDependenciesProvider -> this
        is Application -> error("Application should implements MoviesFeatureDependenciesProvider")
        else -> applicationContext.moviesDependenciesProvider
    }