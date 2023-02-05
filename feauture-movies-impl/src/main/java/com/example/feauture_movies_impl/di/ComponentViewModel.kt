package com.example.feauture_movies_impl.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ComponentViewModel (application: Application): AndroidViewModel(application) {

    val contactsListComponent: MoviesFeatureComponent by lazy {
        MoviesFeatureComponent.initAndGet(application.moviesDependenciesProvider.dependencies)
    }
}