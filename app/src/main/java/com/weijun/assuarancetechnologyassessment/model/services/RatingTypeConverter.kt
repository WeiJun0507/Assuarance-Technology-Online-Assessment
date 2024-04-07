package com.weijun.assuarancetechnologyassessment.model.services

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.weijun.assuarancetechnologyassessment.model.data.Rating
import java.lang.reflect.Type

class RatingTypeConverter {
    @TypeConverter
    fun fromList(ratingList: List<Rating?>?): String? {
        if (ratingList == null) {
            return null
        }
        val type: Type = object : TypeToken<List<Rating?>?>() {}.type
        return Gson().toJson(ratingList, type)
    }

    @TypeConverter
    fun fromString(ratingString: String?): List<Rating>? {
        if (ratingString == null) {
            return null
        }
        val type: Type = object : TypeToken<List<Rating?>?>() {}.type
        return Gson().fromJson(ratingString, type)
    }
}