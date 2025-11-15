package com.example.fitnesapp.data.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "weekly_progress")
data class WeeklyProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val Id: Int=0,
    val exerciseId: Int,
    val total: Int,
    val completed: Int
)

data class TrackedIn(
    @Embedded val exercise: ExerciseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseId"
    )
    val weeklyProgressEntity: WeeklyProgressEntity
)