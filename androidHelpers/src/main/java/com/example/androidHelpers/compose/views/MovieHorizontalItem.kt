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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.androidhelpers.R

@Composable
fun MovieHorizontalItem(thumbnailUrl: String, movieName : String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(150.dp)
            .padding(5.dp)
            .height(180.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
    ) {
        Box(modifier = Modifier.height(250.dp)) {
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
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(movieName, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}