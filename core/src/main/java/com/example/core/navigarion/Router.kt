package com.example.core.navigarion

import kotlinx.coroutines.flow.StateFlow

interface Router {
    val navigationCommand: StateFlow<NavigationDestination>
    fun navigateTo(destination: NavigationDestination)
}
