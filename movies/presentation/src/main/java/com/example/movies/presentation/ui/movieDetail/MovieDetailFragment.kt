package com.example.movies.presentation.ui.movieDetail

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidHelpers.extensions.showToast
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {

    private val viewModel: MovieDetailViewModel by viewModels()

    var movieDetail: MutableState<MovieDetail?> = mutableStateOf(null)
    private var isLoadingVisible: MutableState<Boolean> = mutableStateOf(false)

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                InitializeStateVariables()
                reviewChangeStatesUi()
                DetailScreen(movieDetail.value, isLoadingVisible.value)
            }
        }
    }


    @Composable
    private fun InitializeStateVariables() {
        movieDetail = remember { mutableStateOf(null) }
        isLoadingVisible = remember { mutableStateOf(false) }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector", "CoroutineCreationDuringComposition")
    private fun reviewChangeStatesUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is MovieDetailUiState.Init -> isLoadingVisible.value = false
                        is MovieDetailUiState.Loading -> isLoadingVisible.value = true
                        is MovieDetailUiState.GetDetailInformation -> setSuccessMovieDetail(uiState)
                        is MovieDetailUiState.Error -> showError(uiState)
                    }
                }
            }
        }
    }

    private fun setSuccessMovieDetail(uiState: MovieDetailUiState.GetDetailInformation) {
        movieDetail.value = uiState.movieDetail
        isLoadingVisible.value = false
    }

    private fun showError(uiState: MovieDetailUiState.Error) {
        requireContext().showToast(uiState.message)
        isLoadingVisible.value = false
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailMovie(args.movieId)
    }

}