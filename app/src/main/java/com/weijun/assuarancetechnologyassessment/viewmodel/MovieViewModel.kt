package com.weijun.assuarancetechnologyassessment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weijun.assuarancetechnologyassessment.api.ApiService
import com.weijun.assuarancetechnologyassessment.model.data.MovieData
import com.weijun.assuarancetechnologyassessment.model.repositories.MovieRepository
import com.weijun.assuarancetechnologyassessment.utilities.SnackBarManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val movieRepository: MovieRepository, imdbId: String): ViewModel() {
    var movie: MovieData = movieRepository.getMovieById(imdbId)

    var movieLiveData: MutableLiveData<MovieData> = MutableLiveData<MovieData>()

    init {
        fetchMovieDetail(imdbId)
    }

    private fun fetchMovieDetail(imdbId: String) {
        val remoteMovieCall: Call<MovieData> = ApiService.apiService.getMovieDetail(imdbId)
        remoteMovieCall.enqueue(object : Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (response.body()?.response == "True") {
                            movieLiveData.value = it
                            upsertMovie(it)
                            return@let
                        }

                        // handle error
//                        SnackBarManager.showErrorSnackBar(, "Invalid email or password")
                    }
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.e("MovieViewModel", "fetchMovieDetail: ${t.message}")
            }
        })

    }

    fun upsertMovie(movie: MovieData) {
        movieRepository.upsertMovie(movie)
    }

    fun delete(movie: MovieData) {
        movieRepository.deleteMovie(movie)
    }

}