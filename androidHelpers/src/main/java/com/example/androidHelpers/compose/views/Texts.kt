package com.example.androidHelpers.compose.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidHelpers.compose.styles.getTitleTextStyle

@Composable
fun TextTitle(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier, text = text, style = getTitleTextStyle())
}