package com.weijun.assuarancetechnologyassessment.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.weijun.assuarancetechnologyassessment.model.data.MovieData
import com.weijun.assuarancetechnologyassessment.model.data.MovieListData
import com.weijun.assuarancetechnologyassessment.model.dao.MovieDao
import com.weijun.assuarancetechnologyassessment.model.dao.MovieListDao
import com.weijun.assuarancetechnologyassessment.model.services.RatingTypeConverter

@Database(
    entities = [
        MovieListData::class,
        MovieData::class,
    ], version = 1
)
@TypeConverters(RatingTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieListDao(): MovieListDao
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "assuarance_tech_movie"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}