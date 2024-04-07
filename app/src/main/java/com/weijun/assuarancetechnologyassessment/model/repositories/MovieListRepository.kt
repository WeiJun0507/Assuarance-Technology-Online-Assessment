package com.weijun.assuarancetechnologyassessment.model.repositories

import com.weijun.assuarancetechnologyassessment.model.dao.MovieListDao
import com.weijun.assuarancetechnologyassessment.model.data.MovieListData
import kotlinx.coroutines.flow.Flow

class MovieListRepository(private val movieListDao: MovieListDao) {
    //Get the data from the DB
    val allMovies: List<MovieListData> = movieListDao.getAll()

    // Get the data from the DB as a flow
    val allMoviesFlow: Flow<List<MovieListData>> = movieListDao.getAllFlow()

    //Insert the element to the DB
    fun insertMovieList(movie: List<MovieListData>) {
        movieListDao.insertAll(*movie.toTypedArray())
    }

    //Update the selected element title
    fun updateMovieList(movieList: MovieListData) {
        movieListDao.update(movieList)
    }

    //Delete the selected item
    fun delete(ids: List<Int>) {
        movieListDao.delete(ids = ids)
    }
}