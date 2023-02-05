package com.example.feature_movie_details.di

import android.app.Application
import android.content.Context

interface MovieDetailsFeatureDependenciesProvider {
    val detailsDependencies: MovieDetailsFeatureDependencies
}

val Context.moviesDependenciesProvider: MovieDetailsFeatureDependenciesProvider
    get() = when(this) {
        is MovieDetailsFeatureDependenciesProvider -> this
        is Application -> error("Application should implements MovieDetailsFeatureDependenciesProvider")
        else -> applicationContext.moviesDependenciesProvider
    }