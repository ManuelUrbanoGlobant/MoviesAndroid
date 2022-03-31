package com.example.movies.presentation.ui.moviesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.movies.domain.entities.Movie
import com.example.movies.presentation.R

@Composable
fun MovieItem(movie: Movie?, onClickFavorite: (Boolean) -> Unit) {
    val dimension4Dp = dimensionResource(id = R.dimen.size_4_DP)
    val dimension8Dp = dimensionResource(id = R.dimen.size_8_DP)
    var isFavorite by remember { mutableStateOf(movie?.isFavorite ?: false) }

    Card(
        modifier = Modifier
            .padding(
                dimension8Dp,
                dimension4Dp
            )
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_8_DP)),
        elevation = dimension4Dp
    )
    {
        Surface {
            Row(
                Modifier
                    .padding(dimension4Dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = movie?.getCompleteUrlToDetails())
                            .apply(block = fun ImageRequest.Builder.() {
                                scale(Scale.FIT)
                                placeholder(R.drawable.movie_placeholder)
                            }).build()
                    ),
                    contentDescription = movie?.overview,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(15f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(dimension4Dp)
                        .fillMaxHeight()
                        .weight(75f)
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
                            .padding(dimension4Dp)
                    )
                    Text(
                        text = movie?.overview ?: "",
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Image(
                    modifier = Modifier
                        .padding(dimension4Dp)
                        .fillMaxHeight()
                        .weight(10f)
                        .clickable {
                            isFavorite = !isFavorite
                            onClickFavorite(isFavorite)
                        },
                    painter = getIconFavorite(isFavorite),
                    contentDescription = null,
                )

            }
        }
    }
}

@Composable
private fun getIconFavorite(isFavorite: Boolean) =
    if (isFavorite) painterResource(id = R.drawable.ic_favorite) else painterResource(
        id = R.drawable.ic_favorite_border
    )

@Preview
@Composable
private fun MovieItemPreview() {
    MovieItem(Movie(name = "Avengers", date = "02/03/2022", isFavorite = true)) {

    }
}