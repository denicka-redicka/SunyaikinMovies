package com.example.favorites.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favorites.di.FavoritesComponentViewModel
import com.example.feature_favorites.R
import javax.inject.Inject

class FavoritesFragment : Fragment(R.layout.fragment_favorites_layout) {

    @Inject
    lateinit var moviesViewModelFactory: dagger.Lazy<FavoritesViewModel.MoviesVmFactory>
    @Inject
    lateinit var favoritesAdapter: FavoritesAdapter

    private val viewModel: FavoritesViewModel by viewModels {
        moviesViewModelFactory.get()
    }
    private val componentViewModel: FavoritesComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.favoritesComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritesList = view.findViewById<RecyclerView>(R.id.favoritesList)
        favoritesAdapter.handleLongTouch = viewModel::removeFromFavoritesList
        favoritesList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        favoritesList.adapter = favoritesAdapter

        lifecycleScope.launchWhenCreated {
            viewModel.detailsLiveData.collect { details ->
                favoritesAdapter.submitList(details)
            }
        }

        if (savedInstanceState == null) {
            viewModel.getAllFavorites()
        }
    }
}