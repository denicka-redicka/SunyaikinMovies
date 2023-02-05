package com.example.sunyaikinmovies

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core.data.Router
import com.example.feature_movie_details.view.MovieDetailsFragment
import com.example.feauture_movies_impl.view.MoviesFragment

class MainActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.apply {
            statusBarColor = Color.TRANSPARENT
        }

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MoviesFragment.create(this),
                MoviesFragment::class.java.simpleName
            )
            .commit()
    }

    override fun routeToMovieDetails(id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieDetailsFragment.create(id, this),
                MoviesFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MovieDetailsFragment::class.java.simpleName}")
            .commit()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }
}