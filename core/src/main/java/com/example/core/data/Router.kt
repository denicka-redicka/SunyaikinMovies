package com.example.core.data

interface Router {

    fun routeToMoviesList()

    fun routeToMovieDetails(id: Int)

    fun back()
}