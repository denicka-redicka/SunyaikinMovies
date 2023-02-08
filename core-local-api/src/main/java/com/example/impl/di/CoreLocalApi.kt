package com.example.impl.di

import com.example.impl.data.MoviesLocalDataSource

interface CoreLocalApi<Favorites> {

    fun getLocalDataSource(): MoviesLocalDataSource<Favorites>
}