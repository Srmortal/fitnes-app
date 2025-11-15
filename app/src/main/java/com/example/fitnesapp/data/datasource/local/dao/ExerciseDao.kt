package com.example.fitnesapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesapp.data.model.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Upsert
    suspend fun upsertExercise(exercise: ExerciseEntity)
    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<ExerciseEntity>>
    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    fun getExerciseById(exerciseId: Int): Flow<ExerciseEntity>
}