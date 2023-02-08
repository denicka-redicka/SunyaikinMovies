package com.example.favorites.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class FavoritesComponentViewModel (application: Application): AndroidViewModel(application) {

    val favoritesComponent: FavoritesFeatureComponent by lazy {
        FavoritesFeatureComponent.initAndGet(application.favoritesDependenciesProvider.favoritesDependencies)
    }
}