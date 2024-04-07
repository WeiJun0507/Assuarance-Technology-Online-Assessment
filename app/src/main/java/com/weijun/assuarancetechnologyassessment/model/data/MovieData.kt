package com.weijun.assuarancetechnologyassessment.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie", indices = [Index(value = ["imdb_id"], unique = true)])
data class MovieData(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @SerializedName("Title")
    @ColumnInfo(name="title") val title: String,
    @SerializedName("Year")
    @ColumnInfo(name="year") val year: String,
    @SerializedName("Rated")
    @ColumnInfo(name="rated") val rated: String,
    @SerializedName("Released")
    @ColumnInfo(name="released") val released: String,
    @SerializedName("Runtime")
    @ColumnInfo(name="runtime") val runtime: String,
    @SerializedName("Genre")
    @ColumnInfo(name="genre") val genre: String,
    @SerializedName("Director")
    @ColumnInfo(name="director") val director: String,
    @SerializedName("Writer")
    @ColumnInfo(name="writer") val writer: String,
    @SerializedName("Actors")
    @ColumnInfo(name="actors") val actors: String,
    @SerializedName("Plot")
    @ColumnInfo(name="plot") val plot: String,
    @SerializedName("Language")
    @ColumnInfo(name="language") val language: String,
    @SerializedName("Country")
    @ColumnInfo(name="country") val country: String,
    @SerializedName("Awards")
    @ColumnInfo(name="awards") val awards: String,
    @SerializedName("Poster")
    @ColumnInfo(name="poster") val poster: String,
    @SerializedName("Ratings")
    @ColumnInfo(name="ratings") val ratings: List<Rating> = emptyList(),
    @SerializedName("Metascore")
    @ColumnInfo(name="meta_score") val metaScore: String,
    @SerializedName("imdbRating")
    @ColumnInfo(name="imdb_rating") val imdbRating: String,
    @SerializedName("imdbVotes")
    @ColumnInfo(name="imdb_votes") val imdbVotes: String,
    @SerializedName("imdbID")
    @ColumnInfo(name="imdb_id") val imdbID: String,
    @SerializedName("Type")
    @ColumnInfo(name="typ") val typ: String,
    @SerializedName("DVD")
    @ColumnInfo(name="dvd") val dvd: String,
    @SerializedName("BoxOffice")
    @ColumnInfo(name="box_office") val boxOffice: String,
    @SerializedName("Production")
    @ColumnInfo(name="production") val production: String,
    @SerializedName("Website")
    @ColumnInfo(name="website") val website: String,
    @SerializedName("Response")
    @ColumnInfo(name="response") val response: String,
)

data class Rating(
    @SerializedName("Source")
    val source: String,
    @SerializedName("Value")
    val value: String
)