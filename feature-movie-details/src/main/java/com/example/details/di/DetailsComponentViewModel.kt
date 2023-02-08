package com.example.details.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class DetailsComponentViewModel (application: Application): AndroidViewModel(application) {

    val movieDetailsComponent: MovieDetailsFeatureComponent by lazy {
        MovieDetailsFeatureComponent.initAndGet(application.moviesDependenciesProvider.detailsDependencies)
    }
}