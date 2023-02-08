package com.example.favorites.di

import android.app.Application
import android.content.Context

interface FavoritesFeatureDependenciesProvider {
    val favoritesDependencies: FavoritesFeatureDependencies
}

val Context.favoritesDependenciesProvider: FavoritesFeatureDependenciesProvider
    get() = when(this) {
        is FavoritesFeatureDependenciesProvider -> this
        is Application -> error("Application should implements FavoritesFeatureDependenciesProvider")
        else -> applicationContext.favoritesDependenciesProvider
    }