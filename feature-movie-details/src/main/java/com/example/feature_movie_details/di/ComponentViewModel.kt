package com.example.feature_movie_details.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ComponentViewModel (application: Application): AndroidViewModel(application) {

    val contactsListComponent: MovieDetailsFeatureComponent by lazy {
        MovieDetailsFeatureComponent.initAndGet(application.moviesDependenciesProvider.detailsDependencies)
    }
}