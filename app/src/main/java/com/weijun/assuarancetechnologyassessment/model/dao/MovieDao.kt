package com.weijun.assuarancetechnologyassessment.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.weijun.assuarancetechnologyassessment.model.data.MovieData
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie WHERE imdb_id = :id")
    fun getMovie(id: String): MovieData

    @Upsert
    fun upsert(movie: MovieData): Long

    @Delete
    fun delete(movie: MovieData)
}