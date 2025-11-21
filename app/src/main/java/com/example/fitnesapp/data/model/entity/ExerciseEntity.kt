package com.example.fitnesapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val difficulty: String,
    val imageUrl: String,
    val duration: Int,
    @ColumnInfo(name = "is_completed", defaultValue = "false")
    val isCompleted: Boolean = false
)
