package com.example.movies.presentation.ui.mainList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import com.example.androidHelpers.compose.views.MovieLottieAnimation
import com.example.kotlinhelpers.Constants
import com.example.movies.domain.entities.Movie
import com.example.movies.presentation.R


@Composable
fun HorizontalMovieList(
    movieList: List<Movie>?,
    isLoadingVisible: Boolean = false,
    onNavigate: (NavDeepLinkRequest) -> Unit,
    onNavigateDetail: (NavDirections) -> Unit
) {
    Row {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            if (isLoadingVisible) {
                MovieLottieAnimation()
            } else {
                ContentListMovies(movieList, onNavigate, onNavigateDetail)
            }
        }
    }
}

@Composable
fun ContentListMovies(
    movieList: List<Movie>?,
    onNavigate: (NavDeepLinkRequest) -> Unit,
    onNavigateDetail: (NavDirections) -> Unit
) {
    LazyColumn {
        item { HeaderSection(title = stringResource(id = R.string.most_popular_section)) }
        item {
            movieList?.let {
                LazyRow {
                    itemsIndexed(items = movieList) { _, item ->
                        Box(Modifier.clickable {
                            val navDirection =
                                MainListFragmentDirections.actionMainListFragment2ToMovieDetailFragment(
                                    movieId = item.id
                                )
                            onNavigateDetail(navDirection)
                        }) {
                            MovieHorizontalItem(item)
                        }
                    }
                }
            }
        }
        item {
            FooterInfoSection(
                title = stringResource(id = R.string.text_more),
                onNavigate = onNavigate
            )
        }
        item { HeaderSection(title = stringResource(id = R.string.other_sections_soon)) }
    }
}

@Composable
fun HeaderSection(title: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = title,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun FooterInfoSection(title: String, onNavigate: (NavDeepLinkRequest) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .padding(8.dp)
                .clickable {
                    val request = NavDeepLinkRequest.Builder
                        .fromUri(Constants.LIST_MOVIES_URI.toUri())
                        .build()

                    onNavigate(request)
                }, contentAlignment = Alignment.TopEnd
        ) {
            Text(
                text = title,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}