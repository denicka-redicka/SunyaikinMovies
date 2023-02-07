package com.example.core.navigarion

sealed class NavigationDestination(var handled: Boolean) {
    object MoviesListNavigation: NavigationDestination(false)
    object FavoritesListNavigation: NavigationDestination(false)
    data class MovieDetailsNavigation(val movieId: Int): NavigationDestination(false)
    object Back: NavigationDestination(false)
}
