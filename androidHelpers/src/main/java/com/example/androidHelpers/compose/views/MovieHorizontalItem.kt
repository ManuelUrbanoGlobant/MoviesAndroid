package com.example.androidHelpers.compose.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.androidHelpers.compose.styles.getMovieItemNameTextStyle
import com.example.androidhelpers.R

@Composable
fun MovieHorizontalItem(thumbnailUrl: String, movieName : String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.movie_item_card_width))
            .padding(dimensionResource(id = R.dimen.padding_small))
            .height(dimensionResource(id = R.dimen.movie_item_card_height)),
        shape = RoundedCornerShape(10.dp),
        elevation = dimensionResource(id = R.dimen.elevation_small),
    ) {
        Box(modifier = Modifier.height(dimensionResource(id = R.dimen.movie_item_box_height))) {
            Image(
                painter = rememberAsyncImagePainter(thumbnailUrl),
                contentDescription = stringResource(R.string.movie_item_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(movieName, style = getMovieItemNameTextStyle())
            }
        }
    }
}