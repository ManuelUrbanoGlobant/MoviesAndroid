package com.example.movies.presentation.ui.movieDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidHelpers.compose.views.MovieLottieAnimation
import com.example.androidHelpers.compose.views.TextTitle
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.presentation.R

@Composable
fun DetailScreen(movieDetail: MovieDetail?, isLoadingVisible: Boolean = false) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
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
        }

        if (isLoadingVisible) {
            MovieLottieAnimation()
        }
    }

}

@Composable
private fun ContentDetail(movieDetail: MovieDetail?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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
private fun getTimeFormat(movieDetail: MovieDetail?) =
    if (movieDetail?.time != null) "Time: ${movieDetail.time} Minutes" else ""

@Preview
@Composable
private fun Preview() {
    val detail = MovieDetail(
        name = "Sonic the Hedgehog 2",
        date = "2022-03-30",
        time = 122,
        overview = "After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero. His test comes when Dr. Robotnik returns, this time with a new partner, Knuckles, in search for an emerald that has the power to destroy civilizations. Sonic teams up with his own sidekick, Tails, and together they embark on a globe-trotting journey to find the emerald before it falls into the wrong hands",
        score = 0.0f,
        thumbnail = "/lrP1TQf3stZveNEyviUUcSh8HLA.jpg"
    )
    DetailScreen(detail, false)
}