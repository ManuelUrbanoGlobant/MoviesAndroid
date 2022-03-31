package com.example.movies.presentation.ui.moviesList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDirections
import com.example.androidHelpers.compose.views.MovieLottieAnimation
import com.example.movies.domain.entities.Movie
import com.example.movies.presentation.R


@Composable
fun MovieList(
    movieList: List<Movie>?,
    isLoadingVisible: Boolean = false,
    onNavigate: (NavDirections) -> Unit,
    viewModel: MoviesListViewModel
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoadingVisible) {
            MovieLottieAnimation()
        } else {
            ContentListMovies(movieList, onNavigate, viewModel)
        }
    }
}

@Composable
fun ContentListMovies(
    movieList: List<Movie>?,
    onNavigate: (NavDirections) -> Unit,
    viewModel: MoviesListViewModel
) {
    val listState = rememberLazyListState()
    movieList?.let {
        LazyColumn(state = listState) {
            itemsIndexed(items = movieList) { _, item ->
                if (item.id > 0) {
                    BoxItem(item, onNavigate, onClickFavorite = {
                        viewModel.changeStateFavorite(it, item)
                    })
                } else if (item.id == 0) {
                    BoxItemLoading()
                }
            }
        }
        listState.OnBottomReached(buffer = 1) {
            viewModel.getMoviesList()
        }
    }
}

@Composable
fun BoxItemLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CircularProgressIndicator()
            Text(text = stringResource(id = R.string.loading))
        }
    }
}

@Composable
fun BoxItem(item: Movie, onNavigate: (NavDirections) -> Unit, onClickFavorite: (Boolean) -> Unit) {
    Box(modifier = Modifier
        .clickable {
            val navDirection =
                MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(
                    movieId = item.id
                )
            onNavigate(navDirection)
        }
    ) {
        MovieItem(item, onClickFavorite = onClickFavorite)
    }
}

@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 0,
    loadMore: () -> Unit
) {
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    // Convert the state into a cold flow and collect
    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                // if should load more, then invoke loadMore
                if (it) loadMore()
            }
    }
}
