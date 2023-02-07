package com.example.sunyaikinmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.lifecycleScope
import com.example.core.navigarion.NavigationDestination
import com.example.core.navigarion.Router
import com.example.core.ui.toGoneIf
import com.example.feature_movie_details.view.MovieDetailsFragment
import com.example.feauture_movies_impl.view.MoviesFragment
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

        lifecycleScope.launchWhenStarted {
            router.navigationCommand.collect { destination ->
                val check = destination is NavigationDestination.MovieDetailsNavigation
                headerLayout.toGoneIf(check)
                if (!destination.handled) {
                    handleNavigationEffect(destination)
                    destination.handled = true
                }
            }
        }

    }

    private fun handleNavigationEffect(
        destination: NavigationDestination
    ) {
        when (destination) {
            NavigationDestination.Back -> back()
            NavigationDestination.FavoritesListNavigation -> {}
            is NavigationDestination.MovieDetailsNavigation -> routeToMovieDetails(
                destination.movieId
            )
            NavigationDestination.MoviesListNavigation -> routeToMoviesList()
        }
    }

    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MoviesFragment(),
                MoviesFragment::class.java.simpleName
            )
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
}