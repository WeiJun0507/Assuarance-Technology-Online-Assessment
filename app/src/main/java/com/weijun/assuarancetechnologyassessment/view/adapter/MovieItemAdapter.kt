package com.weijun.assuarancetechnologyassessment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.weijun.assuarancetechnologyassessment.R
import com.weijun.assuarancetechnologyassessment.model.data.MovieListData

class MovieItemAdapter(
    private val context: Context,
    private val movieListData: List<MovieListData>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.movie_poster)
        val title: TextView = itemView.findViewById(R.id.movie_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClick(position)
        }
        holder.title.text = movieListData[position].title
        Glide.with(context).load(movieListData[position].poster)
            .apply(RequestOptions().transform(RoundedCorners(32)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.poster)
    }

    override fun getItemCount(): Int {
        return movieListData.size
    }
}