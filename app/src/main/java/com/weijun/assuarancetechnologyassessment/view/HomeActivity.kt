package com.weijun.assuarancetechnologyassessment.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.weijun.assuarancetechnologyassessment.R
import com.weijun.assuarancetechnologyassessment.api.ApiService
import com.weijun.assuarancetechnologyassessment.databinding.HomeViewBinding
import com.weijun.assuarancetechnologyassessment.model.data.MovieListData
import com.weijun.assuarancetechnologyassessment.model.data.RequestWrapperData
import com.weijun.assuarancetechnologyassessment.model.database.AppDatabase
import com.weijun.assuarancetechnologyassessment.view.adapter.MovieItemAdapter
import com.weijun.assuarancetechnologyassessment.viewmodel.MovieListViewModel
import com.weijun.assuarancetechnologyassessment.viewmodel.MovieListViewModelFactory
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var homeViewBinding: HomeViewBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var appDatabase: AppDatabase

    private var movieList: MutableList<MovieListData> = mutableListOf<MovieListData>()

    private var movieListAdapter: MovieItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewBinding = HomeViewBinding.inflate(layoutInflater)
        appDatabase = AppDatabase.getDatabase(this)
        MovieListViewModelFactory(this).also {
            viewModel = viewModels<MovieListViewModel> { it }.value
        }

        lifecycleScope.launch {
            viewModel.allMoviesFlow.collect {
                movieList.clear()
                movieList.addAll(it)

                if (movieListAdapter == null) {
                    setupMovieList(movieList)
                } else {
                    movieListAdapter?.notifyDataSetChanged()
                }
            }
        }

        setContentView(homeViewBinding.root)

        val topicTextView: TextView = findViewById(R.id.movie_topic)
        topicTextView.text = "Marvel"

        // check db exist data
        // recycler view adapter data binding
        if (viewModel.allMovies.isNotEmpty()) {
            movieList.addAll(viewModel.allMovies)
            setupMovieList(movieList)
        }

        // remote exist data
        lifecycleScope.launch {
            viewModel.getMovieListRemote()
        }
    }

    private fun setupMovieList(movieList: List<MovieListData>) {
        movieListAdapter = MovieItemAdapter(this, movieList, onClick = this::onMovieItemClickListener)
        val gridLayoutManager = GridLayoutManager(this, 2)
        homeViewBinding.movieList.layoutManager = gridLayoutManager
        homeViewBinding.movieList.adapter = movieListAdapter

        homeViewBinding.movieListProgressBar.visibility = View.GONE
        homeViewBinding.homeView.visibility = View.VISIBLE
    }

    private fun onMovieItemClickListener(position: Int) {
        val imdbId = movieList[position].imdbID
        Intent(this, MovieDetailActivity::class.java).also {
            it.putExtra("imdbId", imdbId)
            startActivity(it)
        }
    }
}