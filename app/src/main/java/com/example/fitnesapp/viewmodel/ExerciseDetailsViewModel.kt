package com.example.fitnesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesapp.data.repository.ExerciseRepository
import com.example.fitnesapp.data.repository.WeeklyProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ExerciseDetailsViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val weeklyProgressRepository: WeeklyProgressRepository,
    private val exerciseId: Int
): ViewModel() {
    private val _isCompleted = MutableStateFlow(false)
    val isCompleted: StateFlow<Boolean> = _isCompleted

    init {
        viewModelScope.launch {
            exerciseRepository.getExerciseById(exerciseId = exerciseId).collect { exerciseDetails ->
                _isCompleted.value = exerciseDetails.isCompleted
            }
        }
    }
    fun markAsCompleted() {
        viewModelScope.launch {
            val exerciseDetails = exerciseRepository.getExerciseById(exerciseId = exerciseId).first()

            // If the exercise is already completed, do nothing to prevent re-incrementing.
            if (!exerciseDetails.isCompleted) {
                // Update the exercise to be completed.
                exerciseRepository.upsertExercise(exerciseDetails.copy(isCompleted = true))

                // Get the CURRENT state of the weekly progress, then stop listening.
                val currentProgress = weeklyProgressRepository.getWeeklyProgressById(id = 1).first()

                // Update the weekly progress.
                weeklyProgressRepository.upsertWeeklyProgress(
                    currentProgress.copy(completed = currentProgress.completed + 1)
                )
            }
        }
    }
}

class ExerciseDetailsViewModelFactory(
    private val exerciseRepository: ExerciseRepository,
    private val weeklyProgressRepository: WeeklyProgressRepository,
    private val exerciseId: Int
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseDetailsViewModel::class.java)) {
            return ExerciseDetailsViewModel(exerciseRepository, weeklyProgressRepository, exerciseId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}