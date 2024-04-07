package com.weijun.assuarancetechnologyassessment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.weijun.assuarancetechnologyassessment.api.ApiService.Companion.apiService
import com.weijun.assuarancetechnologyassessment.model.data.MovieListData
import com.weijun.assuarancetechnologyassessment.model.data.RequestWrapperData
import com.weijun.assuarancetechnologyassessment.model.repositories.MovieListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(private val movieListRepository: MovieListRepository): ViewModel() {
    var allMovies: List<MovieListData> = movieListRepository.allMovies

    var allMoviesFlow: Flow<List<MovieListData>> = movieListRepository.allMoviesFlow

    fun insertMovieList(movie: List<MovieListData>) {
        movieListRepository.insertMovieList(movie)
    }
    fun updateMovieList(movieList: MovieListData) {
        movieListRepository.updateMovieList(movieList)
    }
    fun delete(ids: List<Int>) {
        movieListRepository.delete(ids)
    }

    fun getMovieListRemote() {
        val remoteMovieListCall: Call<RequestWrapperData> = apiService.getMovieList()
        remoteMovieListCall.enqueue(object : Callback<RequestWrapperData> {
            override fun onResponse(
                call: Call<RequestWrapperData>,
                response: Response<RequestWrapperData>
            ) {
                if (response.isSuccessful) {
                    val responseData: RequestWrapperData = response.body()!!
                    if (responseData.Response == "True") {
                        // db insert
                        insertMovieList(responseData.Search)
                        return
                    }
                }

                // handle error
            }

            override fun onFailure(call: Call<RequestWrapperData>, t: Throwable) {
                // handle error
                Log.d("HomeActivity", "onFailure: ${t.message}")
            }
        })
    }
}