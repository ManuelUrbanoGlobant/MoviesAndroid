package com.example.movies.presentation.ui.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.movies.domain.entities.Movie
import com.example.movies.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : BaseFragment()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val movieList = mutableListOf<Movie>()
        movieList.add(Movie("Batman","10/02/22","the dark night","steven",5,""))
        movieList.add(Movie("Batman","10/02/22","the dark night","steven",5,""))
        movieList.add(Movie("Batman","10/02/22","the dark night","steven",5,""))
        movieList.add(Movie("Batman","10/02/22","the dark night","steven",5,""))
        movieList.add(Movie("Batman","10/02/22","the dark night","steven",5,""))


        return ComposeView(requireContext()).apply {
            setContent {
                MovieList(movieList = movieList)
            }
        }
    }

    @Composable
    fun MovieList(movieList : List<Movie>) {
        LazyRow{
            itemsIndexed(items = movieList) { _, item ->
                Box(modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
                ) {
                    MovieItem(item)
                }
            }
        }
    }
}
