package com.example.impl.di

import com.example.impl.data.MoviesRemoteDataSourceApi

interface CoreNetworkApi<Movies, MoviesInfo> {

    fun moviesRemoteService(): MoviesRemoteDataSourceApi<Movies, MoviesInfo>
}