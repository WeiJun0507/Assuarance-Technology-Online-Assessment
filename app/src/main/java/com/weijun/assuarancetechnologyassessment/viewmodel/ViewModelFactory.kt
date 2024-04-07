package com.weijun.assuarancetechnologyassessment.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weijun.assuarancetechnologyassessment.model.database.AppDatabase
import com.weijun.assuarancetechnologyassessment.model.repositories.MovieListRepository
import com.weijun.assuarancetechnologyassessment.model.repositories.MovieRepository

class MovieListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            val db = AppDatabase.getDatabase(context)
            val repository = MovieListRepository(db.movieListDao())
            return MovieListViewModel(repository) as T
        }

        return super.create(modelClass)
    }
}

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(private val context: Context, private val imdbId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            val db = AppDatabase.getDatabase(context)
            val repository = MovieRepository(db.movieDao())
            return MovieViewModel(repository, imdbId) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}