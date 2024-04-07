package com.weijun.assuarancetechnologyassessment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weijun.assuarancetechnologyassessment.R
import com.weijun.assuarancetechnologyassessment.model.data.Rating

class MovieRatingAdapter(
    private val ratingList: List<Rating>,
) : RecyclerView.Adapter<MovieRatingAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieRatingTitleItem: TextView =
            itemView.findViewById(R.id.movie_detail_rating_title_item)
        val movieRatingPercentItem: TextView =
            itemView.findViewById(R.id.movie_detail_rating_percent_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_rating_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieRatingTitleItem.text = ratingList[position].source
        holder.movieRatingPercentItem.text = ratingList[position].value
    }

    override fun getItemCount(): Int {
        return ratingList.size
    }
}