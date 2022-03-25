package com.example.movies.presentation.ui.moviesList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidHelpers.compose.views.MovieLottieAnimation
import com.example.androidHelpers.extensions.showToast
import com.example.movies.domain.entities.Movie
import com.example.movies.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesListFragment : BaseFragment() {

    private val viewModel: MoviesListViewModel by viewModels()

    private var moviesList: MutableState<List<Movie>?> = mutableStateOf(null)
    private var isLoadingVisible: MutableState<Boolean> = mutableStateOf(false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                InitializeStateVariables()
                reviewChangeStatesUi()
                MovieList(moviesList.value,isLoadingVisible.value)
            }
        }
    }

    @Composable
    private fun InitializeStateVariables() {
        moviesList = remember { mutableStateOf(null) }
        isLoadingVisible = remember { mutableStateOf(false) }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector", "CoroutineCreationDuringComposition")
    private fun reviewChangeStatesUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is MoviesListUiState.Init -> isLoadingVisible.value = false
                        is MoviesListUiState.Loading -> isLoadingVisible.value = true
                        is MoviesListUiState.GetMoviesList -> setSuccessMoviesList(uiState)
                        is MoviesListUiState.Error -> showError(uiState)
                    }
                }
            }
        }
    }

    private fun setSuccessMoviesList(uiState: MoviesListUiState.GetMoviesList) {
        moviesList.value = uiState.moviesList
        isLoadingVisible.value = false
    }

    private fun showError(uiState: MoviesListUiState.Error) {
        requireContext().showToast(uiState.message)
        isLoadingVisible.value = false
    }

    @Composable
    fun MovieList(movieList : List<Movie>?, isLoadingVisible: Boolean = false) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (isLoadingVisible) {
                MovieLottieAnimation()
            } else {
                movieList?.let {
                    LazyColumn {
                        itemsIndexed(items = movieList) { _, item ->
                            Box(modifier = Modifier
                                .clickable {
                                    val navDirection =
                                        MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(
                                            movieId = 100
                                        )
                                    navigateDeepLinkRequest(navDirection)
                                }
                            ) {
                                MovieItem(item)
                            }
                        }
                    }

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMoviesList()
    }
}
