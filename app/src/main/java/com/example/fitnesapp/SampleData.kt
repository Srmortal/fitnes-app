package com.example.fitnesapp

import com.example.fitnesapp.data.model.entity.ExerciseEntity
import com.example.fitnesapp.data.model.entity.WeeklyProgressEntity

object SampleData {
    val sampleExercises = listOf(
        ExerciseEntity(
            name = "Full Body Strength",
            difficulty = "Advanced",
            imageUrl = "https://images.unsplash.com/photo-1541534741688-6078c6bfb5c5?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzdHJlbmd0aCUyMHRyYWluaW5nfGVufDF8fHx8MTc2MzExMTMwN3ww&ixlib=rb-4.1.0&q=80&w=1080",
            duration = 45
        ),
        ExerciseEntity(
            name = "Morning Yoga",
            difficulty = "Beginner",
            imageUrl = "https://images.unsplash.com/photo-1641971216900-6bf0ca329743?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx5b2dhJTIwZXhlcmNpc2V8ZW58MXx8fHwxNzYzMDk5NDMyfDA&ixlib=rb-4.1.0&q=80&w=1080",
            duration = 30
        ),
        ExerciseEntity(
            name = "Speed Running",
            difficulty = "Intermediate",
            imageUrl = "https://images.unsplash.com/photo-1669806954505-936e77929af6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxydW5uaW5nJTIwY2FyZGlvfGVufDF8fHx8MTc2MzAwNDY0NHww&ixlib=rb-4.1.0&q=80&w=1080",
            duration = 20
        ),
        ExerciseEntity(
            name = "Complete Gym Workout",
            difficulty = "Advanced",
            imageUrl = "https://images.unsplash.com/photo-1584827386916-b5351d3ba34b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmaXRuZXNzJTIwd29ya291dCUyMGd5bXxlbnwxfHx8fDE3NjMwOTk0MzJ8MA&ixlib=rb-4.1.0&q=80&w=1080",
            duration = 60
        ),
        ExerciseEntity(
            name = "Pilates & Flexibility",
            difficulty = "Intermediate",
            imageUrl = "https://images.unsplash.com/photo-1655712779546-a1c0ea613cd8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwaWxhdGVzJTIwc3RyZXRjaHxlbnwxfHx8fDE3NjMxMTU5ODV8MA&ixlib=rb-4.1.0&q=80&w=1080",
            duration = 35
        ),
        ExerciseEntity(
            name = "Indoor Cycling",
            difficulty = "Intermediate",
            imageUrl = "https://images.unsplash.com/photo-1605235186531-bbd852b09e69?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjeWNsaW5nJTIwaW5kb29yfGVufDF8fHx8MTc2MzAzNjQ5OHww&ixlib=rb-4.1.0&q=80&w=1080",
            duration = 40
        )
    )
    val sampleWeeklyProgress = WeeklyProgressEntity(
        total = sampleExercises.size,
        completed = sampleExercises.filter { it.isCompleted }.size
    )
}