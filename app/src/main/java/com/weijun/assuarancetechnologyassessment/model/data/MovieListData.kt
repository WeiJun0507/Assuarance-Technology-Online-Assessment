package com.weijun.assuarancetechnologyassessment.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_list", indices = [Index(value = ["imdbID"], unique = true)])
data class MovieListData(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @SerializedName("Title")
    @ColumnInfo(name="title") val title: String,
    @SerializedName("Year")
    @ColumnInfo(name="year") val year: String,
    @SerializedName("imdbID")
    @ColumnInfo(name="imdbID") val imdbID: String,
    @SerializedName("Type")
    @ColumnInfo(name="typ") val typ: String,
    @SerializedName("Poster")
    @ColumnInfo(name="poster") val poster: String,
)
