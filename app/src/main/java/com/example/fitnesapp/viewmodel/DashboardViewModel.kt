package com.example.fitnesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesapp.data.model.entity.ExerciseEntity
import com.example.fitnesapp.data.model.entity.WeeklyProgressEntity
import com.example.fitnesapp.data.model.entity.WorkoutSessionEntity
import com.example.fitnesapp.data.repository.ExerciseRepository
import com.example.fitnesapp.data.repository.WeeklyProgressRepository
import com.example.fitnesapp.data.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val weeklyProgressRepository: WeeklyProgressRepository,
) : ViewModel() {

    private val _exercises = MutableStateFlow<List<ExerciseEntity>>(emptyList())
    val exercises: StateFlow<List<ExerciseEntity>> = _exercises.asStateFlow()

    private val _workoutSessions = MutableStateFlow<List<WorkoutSessionEntity>>(emptyList())
    val workoutSessions: StateFlow<List<WorkoutSessionEntity>> = _workoutSessions.asStateFlow()

    private val _weeklyProgress = MutableStateFlow<List<WeeklyProgressEntity>>(emptyList())
    val weeklyProgress: StateFlow<List<WeeklyProgressEntity>> = _weeklyProgress.asStateFlow()
    init {
        viewModelScope.launch {
            exerciseRepository.getAllExercises().collect {
                _exercises.value = it
            }
        }
        viewModelScope.launch {
            workoutSessionRepository.getAllWorkoutSessions().collect {
                _workoutSessions.value = it
            }
        }
        viewModelScope.launch {
            weeklyProgressRepository.getAllWeeklyProgress().collect {
                _weeklyProgress.value = it
            }
        }
    }
}

class DashboardViewModelFactory(
    private val exerciseRepository: ExerciseRepository,
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val weeklyProgressRepository: WeeklyProgressRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(
                exerciseRepository,
                workoutSessionRepository,
                weeklyProgressRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}