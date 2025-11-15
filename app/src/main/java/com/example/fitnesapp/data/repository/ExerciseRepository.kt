package com.example.fitnesapp.data.repository

import com.example.fitnesapp.data.datasource.local.dao.ExerciseDao
import com.example.fitnesapp.data.model.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(
    private val exerciseDao: ExerciseDao
) {
    fun getAllExercises(): Flow<List<ExerciseEntity>> {
        return exerciseDao.getAllExercises()
    }
    fun getExerciseById(exerciseId: Int): Flow<ExerciseEntity> {
        return exerciseDao.getExerciseById(exerciseId)
    }
    suspend fun upsertExercise(exercise: ExerciseEntity) {
        exerciseDao.upsertExercise(exercise)
    }
}