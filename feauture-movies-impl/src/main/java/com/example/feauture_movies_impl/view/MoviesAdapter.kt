package com.example.feauture_movies_impl.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.core.data.Movie
import com.example.core.navigarion.NavigationDestination
import com.example.core.navigarion.Router
import com.example.core.ui.R
import javax.inject.Inject

class MoviesAdapter @Inject constructor(
    private val router: Router
) :
    PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieComparator) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_movie, parent, false)
        )
    }


    inner class MovieViewHolder(private val movieView: View) : ViewHolder(movieView) {
        private val name: AppCompatTextView = movieView.findViewById(R.id.movieName)
        private val info: AppCompatTextView = movieView.findViewById(R.id.movieInfo)
        private val poster: AppCompatImageView = movieView.findViewById(R.id.moviePoster)
        private val favoriteStar: AppCompatImageButton =
            movieView.findViewById(R.id.favoritesButton)

        fun bind(movie: Movie?) {
            movieView.setOnClickListener {
                router.navigateTo(
                    NavigationDestination.MovieDetailsNavigation(
                        movieId = movie?.movieId ?: -1
                    )
                )
            }
            name.text = movie?.name ?: ""
            info.text = movie?.year ?: ""
            poster.load(movie?.posterUrlPreview ?: "") {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f))
            }

        }
    }

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }
}