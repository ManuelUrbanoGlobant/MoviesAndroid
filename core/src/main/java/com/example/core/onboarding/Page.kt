package com.example.core.onboarding

import androidx.annotation.RawRes

data class Page(
    val title: String,
    val description: String,
    @RawRes val image: Int
)