package com.weijun.assuarancetechnologyassessment.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.weijun.assuarancetechnologyassessment.databinding.MovieDetailViewBinding
import com.weijun.assuarancetechnologyassessment.model.data.MovieData
import com.weijun.assuarancetechnologyassessment.view.adapter.MovieRatingAdapter
import com.weijun.assuarancetechnologyassessment.viewmodel.MovieViewModel
import com.weijun.assuarancetechnologyassessment.viewmodel.MovieViewModelFactory
import jp.wasabeef.glide.transformations.BlurTransformation
import java.text.NumberFormat

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var movieDetailBinding: MovieDetailViewBinding
    private lateinit var viewModel: MovieViewModel

    private var imdbId: String = ""
    private var movie: MutableLiveData<MovieData> = MutableLiveData<MovieData>()

    private var movieRatingAdapter: MovieRatingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.also {
            imdbId = it.extras?.getString("imdbId") ?: ""
        }
        // todo: handle id not found

        movieDetailBinding = MovieDetailViewBinding.inflate(layoutInflater)
        MovieViewModelFactory(this, imdbId).also {
            viewModel = viewModels<MovieViewModel> { it }.value
            viewModel.movieLiveData.observe(this) {
                movie.value = viewModel.movieLiveData.value
                setupMovieView(movie.value!!)
            }
        }

        // Set the window flags
        // Set the status bar icons to appear as dark
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(movieDetailBinding.root)

        movie.value = viewModel.movie

        if (movie.value != null && movie.value!!.imdbID != "") {
            setupMovieView(movie.value!!)
        }

    }

    private fun setupMovieView(data: MovieData) {
        movieDetailBinding.movieDetailBackButton.setOnClickListener {
            finish()
        }
        Glide.with(this)
            .load(data.poster)
            .centerCrop()
            .apply(bitmapTransform(BlurTransformation(15, 2)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(movieDetailBinding.movieDetailPosterBlur)

        Glide.with(this)
            .load(data.poster)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(movieDetailBinding.movieDetailPosterClear)

        movieDetailBinding.movieDetailTitle.text = "${data.title} (${data.year})"
        movieDetailBinding.movieDetailCategory.text = data.genre

        movieDetailBinding.movieDetailPlotDescription.text = data.plot

        movieDetailBinding.movieDetailRatingsBar.rating = data.imdbRating?.let {
            it.toFloat() / 2
        } ?: 0f
        movieDetailBinding.movieDetailRatingsImdb.text = "${data.imdbRating} / 10"
        movieDetailBinding.movieDetailRatingsImdbCount.text = "${
            NumberFormat.getNumberInstance().format(data.imdbRating?.toDouble() ?: 0)
        } Ratings"

        data.ratings.let { rating ->
            movieRatingAdapter = MovieRatingAdapter(rating)
            movieRatingAdapter.apply {
                movieDetailBinding.movieDetailRatingsList.layoutManager = LinearLayoutManager(
                    this@MovieDetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                movieDetailBinding.movieDetailRatingsList.adapter = this
            }
        }

        movieDetailBinding.movieDetailProgressBar.visibility = View.GONE
        movieDetailBinding.movieDetailMainLayout.visibility = View.VISIBLE
    }
}