package com.weijun.assuarancetechnologyassessment.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.weijun.assuarancetechnologyassessment.model.data.MovieListData
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieListDao {
    @Query("SELECT * FROM movie_list")
    fun getAll(): List<MovieListData>

    @Query("SELECT * FROM movie_list")
    fun getAllFlow(): Flow<List<MovieListData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movieItems: MovieListData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movieItem: MovieListData)

    @Query("DELETE FROM movie_list WHERE id IN (:ids)")
    fun delete(ids: List<Int>)
}