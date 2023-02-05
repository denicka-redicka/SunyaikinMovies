package com.example.core_repository_api.di

import com.example.core_repository_api.data.MoviesRepository

interface CoreRepositoryApi <Movies, MoviesInfo> {

    fun getMoviesRepository(): MoviesRepository<Movies, MoviesInfo>
}