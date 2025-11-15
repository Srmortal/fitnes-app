package com.example.fitnesapp.data.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "workout_sessions")
data class WorkoutSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val title: String,
    val description: String,
    val duration: Int,
    val exercises: Int
)

data class Contains(
    @Embedded val exercise: List<ExerciseEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseId"
    )
    val workoutSessionEntity: WorkoutSessionEntity
)
