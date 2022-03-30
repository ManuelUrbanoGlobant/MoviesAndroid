package com.example.movies.presentation.ui.mainList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.androidHelpers.extensions.showToast
import com.example.kotlinhelpers.BaseEvent
import com.example.movies.domain.entities.Movie
import com.example.movies.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainListFragment : BaseFragment() {

    private val viewModel: MainListViewModel by viewModels()

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
                HorizontalMovieList(
                    moviesList.value,
                    isLoadingVisible.value,
                    onNavigate = { dest ->
                        findNavController().navigate(dest)
                    },
                    onNavigateDetail = { dest ->
                        findNavController().navigate(dest)
                    },
                )
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
                        is BaseEvent.Init -> isLoadingVisible.value = false
                        is BaseEvent.Loading -> isLoadingVisible.value = true
                        is MainListUiState.GetMainMoviesList -> setSuccessMoviesList(uiState)
                        is BaseEvent.Error -> showError(uiState)
                    }
                }
            }
        }
    }

    private fun setSuccessMoviesList(uiState: MainListUiState.GetMainMoviesList) {
        moviesList.value = uiState.moviesList
        isLoadingVisible.value = false
    }

    private fun showError(uiState: BaseEvent.Error) {
        requireContext().showToast(uiState.message)
        isLoadingVisible.value = false
    }
}