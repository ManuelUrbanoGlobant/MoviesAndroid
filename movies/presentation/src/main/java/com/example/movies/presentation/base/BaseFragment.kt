package com.example.movies.presentation.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

open class BaseFragment : Fragment() {

    fun navigateDeepLinkRequest(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}