package com.example.core

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToMovies()
    }

    private fun navigateToMovies() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("app://com.example.movies/movies"))
        startActivity(intent)
        finish()
    }
}