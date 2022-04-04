package com.example.core


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.androidHelpers.constants.PreferenceConstants
import com.example.androidHelpers.preferences.StoreHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var storeHelper: StoreHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStartDestination()
    }

    private fun setStartDestination() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_core_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph_core)

        lifecycleScope.launch {
            storeHelper.getBooleanValue(PreferenceConstants.ON_BOARDING_KEY).collectLatest {
                if (it) navGraph.setStartDestination(R.id.mainCoreFragment)
                else navGraph.setStartDestination(
                    R.id.onBoardingFragment
                )
                navController.graph = navGraph
            }
        }
    }
}