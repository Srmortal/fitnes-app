package com.example.fitnesapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesapp.data.model.entity.WorkoutSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSessionDao {
    @Upsert
    suspend fun upsertWorkoutSession(workoutSession: WorkoutSessionEntity)
    @Query("SELECT * FROM workout_sessions")
    fun getAllWorkoutSessions(): Flow<List<WorkoutSessionEntity>>
    @Query("SELECT * FROM workout_sessions WHERE id = :workoutSessionId")
    fun getWorkoutSessionById(workoutSessionId: Int): Flow<WorkoutSessionEntity>
}