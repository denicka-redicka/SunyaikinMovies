package com.example.core.ui.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.core.data.Movie
import com.example.core.navigarion.NavigationDestination
import com.example.core.ui.R
import com.example.core.ui.toGoneIf

class MovieViewHolder(private val movieView: View) : RecyclerView.ViewHolder(movieView) {
    private val name: AppCompatTextView = movieView.findViewById(R.id.movieName)
    private val info: AppCompatTextView = movieView.findViewById(R.id.movieInfo)
    private val poster: AppCompatImageView = movieView.findViewById(R.id.moviePoster)
    private val favoriteStar: AppCompatImageButton =
        movieView.findViewById(R.id.favoritesButton)

    fun bind(
        movie: Movie?,
        navigateTo: (NavigationDestination) -> Unit,
        onStarLongTouched: ((Int, Boolean) -> Boolean)?
    ) {
        movieView.setOnClickListener {
            navigateTo.invoke(
                NavigationDestination.MovieDetailsNavigation(
                    movieId = movie?.movieId ?: -1
                )
            )
        }
        favoriteStar.toGoneIf(!(movie?.isFavorite?: false))
        name.text = movie?.name ?: ""
        info.text = movie?.year ?: ""
        poster.load(movie?.posterUrlPreview ?: "") {
            crossfade(true)
            transformations(RoundedCornersTransformation(15f))
        }

        movieView.setOnLongClickListener {
            movie?.isFavorite = !(movie?.isFavorite?: true)
            favoriteStar.toGoneIf(movie?.isFavorite == false)
            onStarLongTouched?.invoke(movie?.movieId?: -1, movie?.isFavorite?: false)?: false

        }

    }
}