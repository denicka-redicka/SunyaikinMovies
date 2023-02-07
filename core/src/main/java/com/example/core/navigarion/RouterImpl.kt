package com.example.core.navigarion

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object RouterImpl : Router {

    private val _navigationCommand =
        MutableStateFlow<NavigationDestination>(NavigationDestination.MoviesListNavigation)
    override val navigationCommand: StateFlow<NavigationDestination> =
        _navigationCommand.asStateFlow()

    override fun navigateTo(destination: NavigationDestination) {
        destination.handled = false
        _navigationCommand.value = destination
    }
}