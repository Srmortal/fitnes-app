package com.example.fitnesapp.data.repository

import com.example.fitnesapp.data.datasource.local.dao.WorkoutSessionDao
import com.example.fitnesapp.data.model.entity.WorkoutSessionEntity
import kotlinx.coroutines.flow.Flow

class WorkoutSessionRepository(
    private val workoutSessionDao: WorkoutSessionDao
) {
    fun getAllWorkoutSessions(): Flow<List<WorkoutSessionEntity>> {
        return workoutSessionDao.getAllWorkoutSessions()
    }
    suspend fun getWorkoutSessionById(workoutSessionId: Int): Flow<WorkoutSessionEntity> {
        return workoutSessionDao.getWorkoutSessionById(workoutSessionId)
    }
    suspend fun upsertWorkoutSession(workoutSession: WorkoutSessionEntity) {
        workoutSessionDao.upsertWorkoutSession(workoutSession)
    }
}