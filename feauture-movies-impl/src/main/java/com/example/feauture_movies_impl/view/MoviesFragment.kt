package com.example.feauture_movies_impl.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.core.data.Router
import com.example.feauture_movies_impl.R
import com.example.feauture_movies_impl.di.ComponentViewModel
import dagger.Lazy
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesFragment: Fragment(R.layout.fragment_movies_layout) {

    companion object {
        var router: Router? = null
        fun create(router: Router) = MoviesFragment().also {
            this.router = router
        }
    }

    @Inject lateinit var moviesViewModelFactory: Lazy<MoviesViewModel.MoviesVmFactory>
    @Inject lateinit var moviesAdapter: MoviesAdapter

    private val viewModel: MoviesViewModel by viewModels{
        moviesViewModelFactory.get()
    }
    private val componentViewModel: ComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.contactsListComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.moviesState.onEach { movies ->
            moviesAdapter.submitData(movies)
        }.launchIn(lifecycleScope)

    }

    override fun onDetach() {
        super.onDetach()
        router = null
    }
}