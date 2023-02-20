package com.example.sunyaikinmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.lifecycleScope
import com.example.core.navigarion.NavigationDestination
import com.example.core.navigarion.Router
import com.example.core.ui.toGoneIf
import com.example.favorites.view.FavoritesFragment
import com.example.details.view.MovieDetailsFragment
import com.example.feauture_movies_impl.view.MoviesFragment
import com.google.android.material.button.MaterialButton
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val headerLayout = findViewById<LinearLayoutCompat>(R.id.headerLayout)
        val moviesButton = findViewById<MaterialButton>(R.id.moviesButton)
        val favoritesButton = findViewById<MaterialButton>(R.id.favoritesButton)

        lifecycleScope.launchWhenStarted {
            router.navigationCommand.collect { destination ->
                handleNavigationByUi(headerLayout, destination, moviesButton, favoritesButton)

                if (!destination.handled) {
                    handleNavigationEffect(destination)
                }
            }
        }
        moviesButton.setOnClickListener {
            router.navigateTo(NavigationDestination.MoviesListNavigation)
        }

        favoritesButton.setOnClickListener {
            router.navigateTo(NavigationDestination.FavoritesListNavigation)
        }
    }

    private fun handleNavigationByUi(
        headerLayout: LinearLayoutCompat,
        destination: NavigationDestination,
        moviesButton: MaterialButton,
        favoritesButton: MaterialButton
    ) {
        headerLayout.toGoneIf(destination is NavigationDestination.MovieDetailsNavigation)
        moviesButton.toGoneIf(destination is NavigationDestination.MovieDetailsNavigation)
        favoritesButton.toGoneIf(destination is NavigationDestination.MovieDetailsNavigation)
        when (destination) {
            is NavigationDestination.MoviesListNavigation -> {
                val headerText = headerLayout.findViewById<AppCompatTextView>(R.id.fragmentHeader)
                headerText.text = getString(R.string.popular)
            }
            is NavigationDestination.FavoritesListNavigation -> {
                val headerText = headerLayout.findViewById<AppCompatTextView>(R.id.fragmentHeader)
                headerText.text = getString(R.string.favorites)
            }
            else -> return
        }
    }

    private fun handleNavigationEffect(
        destination: NavigationDestination
    ) {
        when (destination) {
            NavigationDestination.Back -> back()
            NavigationDestination.FavoritesListNavigation -> routeToFavoritesList()
            is NavigationDestination.MovieDetailsNavigation -> routeToMovieDetails(
                destination.movieId
            )
            NavigationDestination.MoviesListNavigation -> routeToMoviesList()
        }
        destination.handled = true
    }

    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MoviesFragment(),
                MoviesFragment::class.java.simpleName
            )
            .addToBackStack(MoviesFragment::class.java.simpleName)
            .commit()
    }

    private fun routeToFavoritesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                FavoritesFragment(),
                MoviesFragment::class.java.simpleName
            )
            .addToBackStack(FavoritesFragment::class.java.simpleName)
            .commit()
    }

    private fun routeToMovieDetails(id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieDetailsFragment.create(id),
                MoviesFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MovieDetailsFragment::class.java.simpleName}")
            .commit()
    }

    private fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            super.onBackPressed()
        else
            router.navigateTo(NavigationDestination.Back)
    }
}