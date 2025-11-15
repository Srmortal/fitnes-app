package com.example.fitnesapp.data.repository

import com.example.fitnesapp.data.datasource.local.dao.WeeklyProgressDao
import com.example.fitnesapp.data.model.entity.WeeklyProgressEntity
import kotlinx.coroutines.flow.Flow

class WeeklyProgressRepository(
    private val weeklyProgressDao: WeeklyProgressDao
){
    suspend fun upsertWeeklyProgress(weeklyProgress: WeeklyProgressEntity) {
        weeklyProgressDao.upsertWeeklyProgress(weeklyProgress)
    }
    fun getWeeklyProgressById(id: Int): Flow<WeeklyProgressEntity> {
        return weeklyProgressDao.getWeeklyProgressById(id)
    }
    fun getAllWeeklyProgress(): Flow<List<WeeklyProgressEntity>> {
        return weeklyProgressDao.getAllWeeklyProgress()
    }
}