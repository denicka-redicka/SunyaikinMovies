package com.example.feature_movie_details.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.core.data.MovieDetails
import com.example.core.data.constants.MOVIE_ID_KEY
import com.example.core.navigarion.NavigationDestination
import com.example.core.navigarion.Router
import com.example.feature_movie_details.R
import com.example.feature_movie_details.di.ComponentViewModel
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
    private val componentViewModel: ComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.contactsListComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(MOVIE_ID_KEY) ?: -1

        viewModel.detailsLiveData.observe(viewLifecycleOwner) { details ->
            renderUi(details, view)
        }

        if (savedInstanceState == null) {
            viewModel.getMovieDetails(movieId)
        }

    }

    private fun renderUi(movieDetails: MovieDetails, view: View) {
        val movieName = view.findViewById<AppCompatTextView>(R.id.movieName)
        val poster = view.findViewById<AppCompatImageView>(R.id.moviePoster)
        val description = view.findViewById<AppCompatTextView>(R.id.movieDescription)
        val genres = view.findViewById<AppCompatTextView>(R.id.movieGenres)
        val additional = view.findViewById<AppCompatTextView>(R.id.movieAdditional)
        val backButton = view.findViewById<AppCompatImageButton>(R.id.back)

        movieName.text = movieDetails.name
        description.text = movieDetails.description
        var genresString = ""
        var countries = ""
        movieDetails.genres.forEachIndexed { index, genre ->
            if (index != movieDetails.genres.size - 1)
                genresString += "${genre.name}, "
            else
                genresString += genre.name
        }
        movieDetails.countries.forEachIndexed { index, country ->
            if (index != movieDetails.genres.size - 1)
                countries += "${country.name}, "
            else
                countries += country.name
        }
        genres.text = genresString
        additional.text = countries
        poster.load(movieDetails.posterUrl) {
            crossfade(true)

        }
        backButton.setOnClickListener {
            router.navigateTo(NavigationDestination.Back)
        }
    }
}