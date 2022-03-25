package com.example.core


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidHelpers.constants.PreferenceConstants
import com.example.androidHelpers.preferences.StoreHelper
import com.example.core.onboarding.OnBoardingFragment
import com.example.kotlinhelpers.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var storeHelper: StoreHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reviewOnBoardingStatus()
    }

    private fun addOnBoardingFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container_view, OnBoardingFragment())
        transaction.commit()
    }

    private fun navigateToMovies() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.LIST_MOVIES_URI))
        startActivity(intent)
        finish()
    }

    private fun reviewOnBoardingStatus () {
        lifecycleScope.launch {
            storeHelper.getBooleanValue(PreferenceConstants.ON_BOARDING_KEY).collectLatest {
                if (it) navigateToMovies() else addOnBoardingFragment()
            }
        }
    }
}