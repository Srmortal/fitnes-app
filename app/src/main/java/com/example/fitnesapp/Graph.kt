package com.example.fitnesapp

import android.content.Context
import com.example.fitnesapp.data.datasource.local.database.FitnessDatabase
import com.example.fitnesapp.data.repository.ExerciseRepository
import com.example.fitnesapp.data.repository.WeeklyProgressRepository
import com.example.fitnesapp.data.repository.WorkoutSessionRepository

object Graph {
    lateinit var db: FitnessDatabase
        private set
    val exerciseRepository by lazy {
        ExerciseRepository(
            exerciseDao = db.exerciseDao()
        )
    }
    val workoutSessionRepository by lazy {
        WorkoutSessionRepository(
            workoutSessionDao = db.workoutSessionDao()
        )
    }

    val weeklyProgressRepository by lazy {
        WeeklyProgressRepository(
            weeklyProgressDao = db.weeklyProgressDao()
        )
    }

    fun provide(context: Context){
        db = FitnessDatabase.getInstance(context = context)
    }
}