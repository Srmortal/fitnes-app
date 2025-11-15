package com.example.fitnesapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesapp.data.model.entity.WeeklyProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklyProgressDao {
    @Upsert
    suspend fun upsertWeeklyProgress(weeklyProgress: WeeklyProgressEntity)
    @Query("SELECT * FROM weekly_progress")
    fun getAllWeeklyProgress(): Flow<List<WeeklyProgressEntity>>
    @Query("SELECT * FROM weekly_progress WHERE id = :weeklyProgressId")
    fun getWeeklyProgressById(weeklyProgressId: Int): Flow<WeeklyProgressEntity>
}