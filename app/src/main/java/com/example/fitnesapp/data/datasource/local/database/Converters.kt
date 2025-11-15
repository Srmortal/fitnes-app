package com.example.fitnesapp.data.datasource.local.database

import androidx.room.TypeConverter
import com.example.fitnesapp.data.model.entity.ExerciseEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromExerciseList(value: List<ExerciseEntity>?): String? {
        if (value == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ExerciseEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toExerciseList(value: String?): List<ExerciseEntity>? {
        if (value == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ExerciseEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}