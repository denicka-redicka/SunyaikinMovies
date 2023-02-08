package com.example.details.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.core.data.MovieDetails
import com.example.core.data.constants.MOVIE_ID_KEY
import com.example.core.navigarion.NavigationDestination
import com.example.core.navigarion.Router
import com.example.details.di.DetailsComponentViewModel
import com.example.feature_movie_details.R
import dagger.Lazy
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details_layout) {

    companion object {
        fun create(id: Int) = MovieDetailsFragment().also {
            val args = bundleOf(
                MOVIE_ID_KEY to id
            )
            it.arguments = args
        }
    }

    @Inject
    lateinit var moviesViewModelFactory: Lazy<MovieDetailsViewModel.MoviesVmFactory>

    @Inject
    lateinit var router: Router

    private val viewModel: MovieDetailsViewModel by viewModels {
        moviesViewModelFactory.get()
    }
    private val componentViewModel: DetailsComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.movieDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(MOVIE_ID_KEY) ?: -1

        lifecycleScope.launchWhenCreated {
            viewModel.detailsLiveData.collect { details ->
                renderUi(details, view)
            }
        }

        if (savedInstanceState == null) {
            viewModel.getMovieDetails(movieId)
        }
    }

    private fun renderUi(movieDetails: MovieDetails?, view: View) {
        if (movieDetails != null) {
            val movieName = view.findViewById<AppCompatTextView>(R.id.movieName)
            val poster = view.findViewById<AppCompatImageView>(R.id.moviePoster)
            val description = view.findViewById<AppCompatTextView>(R.id.movieDescription)
            val genres = view.findViewById<AppCompatTextView>(R.id.movieGenres)
            val additional = view.findViewById<AppCompatTextView>(R.id.movieAdditional)
            val backButton = view.findViewById<AppCompatImageButton>(R.id.back)

            movieName.text = movieDetails.name
            description.text = movieDetails.description
            genres.text = movieDetails.genres
            additional.text = movieDetails.countries
            poster.load(movieDetails.posterUrl) {
                crossfade(true)

            }
            backButton.setOnClickListener {
                router.navigateTo(NavigationDestination.Back)
            }
        }
    }
}