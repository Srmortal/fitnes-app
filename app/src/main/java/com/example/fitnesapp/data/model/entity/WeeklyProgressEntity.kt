package com.example.fitnesapp.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weekly_progress")
data class WeeklyProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val Id: Int=0,
    val total: Int,
    val completed: Int
)