package com.example.movies.presentation.ui.moviesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.androidHelpers.compose.views.MovieLottieAnimation
import com.example.movies.domain.entities.Movie

@Composable
fun MovieList(
    movieList: List<Movie>?,
    isLoadingVisible: Boolean = false,
    onNavigate: (NavDirections) -> Unit
) {
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
                                        movieId = item.id
                                    )
                                onNavigate(navDirection)
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

@Composable
fun MovieItem(movie: Movie?) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    )
    {
        Surface() {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = movie?.getCompleteUrlToDetails())
                            .apply(block = fun ImageRequest.Builder.() {
                                scale(Scale.FILL)
                                //  transformations(CircleCropTransformation())
                            }).build()
                    ),
                    contentDescription = movie?.overview,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie?.name ?: "",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie?.score.toString(),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = movie?.overview ?: "",
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }
}
