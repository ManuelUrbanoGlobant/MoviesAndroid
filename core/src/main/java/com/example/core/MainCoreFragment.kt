package com.example.core

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidHelpers.preferences.StoreHelper
import com.example.kotlinhelpers.Constants
import com.example.movies.presentation.base.BaseFragment
import com.example.movies.presentation.ui.mainList.MainListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainCoreFragment : BaseFragment() {

    @Inject
    lateinit var storeHelper: StoreHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_core, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addMoviesSection()
    }

    private fun addMoviesSection() {
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.section_movies, MainListFragment.getInstance(::navigateToMovies))
        transaction.commit()
    }

    private fun navigateToMovies() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.MAIN_LIST_MOVIES_URI))
        startActivity(intent)
    }

}