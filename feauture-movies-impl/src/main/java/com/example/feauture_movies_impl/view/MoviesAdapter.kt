package com.example.feauture_movies_impl.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.core.data.Movie
import com.example.core.navigarion.Router
import com.example.core.ui.R
import com.example.core.ui.viewholder.MovieViewHolder
import javax.inject.Inject

class MoviesAdapter @Inject constructor(
    private val router: Router
) : PagingDataAdapter<Movie, MovieViewHolder>(MovieComparator) {

    var handleLongTouch: ((Int, Boolean) -> Boolean)? = null

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), router::navigateTo, handleLongTouch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_movie, parent, false)
        )
    }

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }
}