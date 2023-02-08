package com.example.feauture_movies_impl.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feauture_movies_impl.R
import com.example.feauture_movies_impl.di.ComponentViewModel
import dagger.Lazy
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MoviesFragment : Fragment(R.layout.fragment_movies_layout) {

    @Inject lateinit var moviesViewModelFactory: Lazy<MoviesViewModel.MoviesVmFactory>
    @Inject lateinit var moviesAdapter: MoviesAdapter

    private val viewModel: MoviesViewModel by viewModels {
        moviesViewModelFactory.get()
    }
    private val componentViewModel: ComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.contactsListComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieList = view.findViewById<RecyclerView>(R.id.moviesList)
        moviesAdapter.handleLongTouch = viewModel::handleOnStarClicked
        movieList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        movieList.adapter = moviesAdapter

        lifecycleScope.launchWhenCreated {
            viewModel.moviesState.collectLatest { movies ->
                Log.d("DEBUG", movies.toString())
                moviesAdapter.submitData(movies)
            }
        }
    }
}