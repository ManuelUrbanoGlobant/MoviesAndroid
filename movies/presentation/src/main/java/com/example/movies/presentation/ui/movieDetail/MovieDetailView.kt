package com.example.movies.presentation.ui.movieDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDirections
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidHelpers.compose.views.MovieHorizontalItem
import com.example.androidHelpers.compose.views.MovieLottieAnimation
import com.example.androidHelpers.compose.views.TextTitle
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.entities.MovieRecommendation
import com.example.movies.presentation.R

@Composable
fun DetailScreen(
    movieDetail: MovieDetail?,
    movieRecommendations: List<MovieRecommendation>?,
    isLoadingVisible: Boolean = false,
    onNavigateDetail: (NavDirections) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoadingVisible) {
            MovieLottieAnimation()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movieDetail?.getCompleteUrlToDetails())
                        .crossfade(true)
                        .build(),
                    placeholder = null,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.height_image_detail))
                )
                ContentDetail(movieDetail)
                ContentRecommendation(movieRecommendations, onNavigateDetail)
            }
        }
    }
}

@Composable
private fun ContentDetail(movieDetail: MovieDetail?) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(dimensionResource(id = R.dimen.padding_view_detail))
    ) {
        TextTitle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_view_detail)),
            text = movieDetail?.name ?: ""
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_view_detail)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextTitle(text = movieDetail?.date ?: "")
            Text(text = getTimeFormat(movieDetail))
        }

        Text(
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_view_detail)),
            text = movieDetail?.overview ?: ""
        )
    }
}

@Composable
private fun ContentRecommendation(
    recommendations: List<MovieRecommendation>?,
    onNavigateDetail: (NavDirections) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(dimensionResource(id = R.dimen.padding_view_detail))
    ) {
        TextTitle(
            text = stringResource(R.string.recommended_movies_title),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_view_detail))
        )
        RecommendationsHorizontalScroll(recommendations, onNavigateDetail)
    }
}

@Composable
private fun RecommendationsHorizontalScroll(
    movieList: List<MovieRecommendation>?,
    onNavigateDetail: (NavDirections) -> Unit,
) {
    movieList?.let {
        LazyRow {
            itemsIndexed(items = movieList) { _, movie ->
                Box(Modifier.clickable {
                    val navDirection =
                        MovieDetailFragmentDirections.toDetailScreen(
                            movieId = movie.id
                        )
                    onNavigateDetail(navDirection)
                }) {
                    MovieHorizontalItem(movie.getCompleteThumbnailUrl(), movie.name)
                }
            }
        }
    }
}

@Composable
private fun getTimeFormat(movieDetail: MovieDetail?) =
    if (movieDetail?.time != null) "Time: ${movieDetail.time} Minutes" else ""

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val detail = MovieDetail(
        id = 1,
        name = "Sonic the Hedgehog 2",
        date = "2022-03-30",
        time = 122,
        overview = "After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero. His test comes when Dr. Robotnik returns, this time with a new partner, Knuckles, in search for an emerald that has the power to destroy civilizations. Sonic teams up with his own sidekick, Tails, and together they embark on a globe-trotting journey to find the emerald before it falls into the wrong hands",
        score = 0.0,
        thumbnail = "/lrP1TQf3stZveNEyviUUcSh8HLA.jpg"
    )

    DetailScreen(detail, movieRecommendations = emptyList())
}