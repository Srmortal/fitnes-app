package com.example.fitnesapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesapp.data.repository.ExerciseRepository
import com.example.fitnesapp.data.repository.WeeklyProgressRepository
import com.example.fitnesapp.data.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val weeklyProgressRepository: WeeklyProgressRepository
): ViewModel() {
    val exercises = exerciseRepository.getAllExercises()
        .catch {
            Log.e("DashboardViewModel", "Error loading exercises", it)
            emit(emptyList())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val workoutSessions = workoutSessionRepository.getAllWorkoutSessions()
        .catch {
            Log.e("DashboardViewModel", "Error loading workout sessions", it)
            emit(emptyList())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val weeklyProgress = weeklyProgressRepository.getAllWeeklyProgress()
        .catch {
            Log.e("DashboardViewModel", "Error loading weekly progress", it)
            emit(emptyList())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}

class DashboardViewModelFactory(
    private val exerciseRepository: ExerciseRepository,
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val weeklyProgressRepository: WeeklyProgressRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(exerciseRepository, workoutSessionRepository, weeklyProgressRepository) as T
    }
}