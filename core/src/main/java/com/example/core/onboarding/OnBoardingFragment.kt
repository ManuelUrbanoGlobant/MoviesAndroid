package com.example.core.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.androidHelpers.constants.PreferenceConstants
import com.example.androidHelpers.preferences.StoreHelper
import com.example.core.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    @Inject
    lateinit var storeHelper: StoreHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                OnBoardingScreen(onGettingStartedClick = ::finishOnBoarding)
            }
        }
    }

    private fun finishOnBoarding() {
        viewLifecycleOwner.lifecycleScope.launch {
            storeHelper.setBooleanValue(PreferenceConstants.ON_BOARDING_KEY, true)
            val navGraph = findNavController().navInflater.inflate(R.navigation.nav_graph_core)
            navGraph.setStartDestination(R.id.mainCoreFragment)
            findNavController().graph = navGraph
        }
    }
}