package com.example.androidHelpers.compose.views

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.androidhelpers.R

@Composable
fun MovieLottieAnimation(@RawRes resource: Int = R.raw.movie_loading) {
    val isPlaying by remember { mutableStateOf(true) }
    val speed by remember { mutableStateOf(1f) }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resource))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false
    )

    LottieAnimation(
        composition,
        progress,
        modifier = Modifier.size(100.dp)
    )

}