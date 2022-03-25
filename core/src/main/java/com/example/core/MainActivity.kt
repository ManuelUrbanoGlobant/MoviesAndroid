package com.example.core

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinhelpers.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToMovies()
    }

    private fun navigateToMovies() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.LIST_MOVIES_URI))
        startActivity(intent)
        finish()
    }
}