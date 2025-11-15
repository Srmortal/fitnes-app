package com.example.fitnesapp.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val difficulty: String,
    val imageUrl: String,
    val duration: Int
)
