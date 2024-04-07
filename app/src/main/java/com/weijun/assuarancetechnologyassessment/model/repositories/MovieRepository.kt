package com.weijun.assuarancetechnologyassessment.model.repositories

import android.util.Log
import com.weijun.assuarancetechnologyassessment.model.dao.MovieDao
import com.weijun.assuarancetechnologyassessment.model.data.MovieData
import com.weijun.assuarancetechnologyassessment.model.data.MovieListData
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {
    //Get the data from the DB
    fun getMovieById(id: String): MovieData = movieDao.getMovie(id)

    //Insert the element to the DB
    fun upsertMovie(movie: MovieData) {
        try {
            movieDao.upsert(movie)
        } catch (e: Exception) {
            Log.d("MovieRepository", "upsertMovie: ${e.message}")
        }
    }

    //Delete the selected item
    fun deleteMovie(movie: MovieData) {
        movieDao.delete(movie)
    }
}