package com.example.fitnesapp.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fitnesapp.data.datasource.local.dao.ExerciseDao
import com.example.fitnesapp.data.datasource.local.dao.WeeklyProgressDao
import com.example.fitnesapp.data.datasource.local.dao.WorkoutSessionDao
import com.example.fitnesapp.data.model.entity.ExerciseEntity
import com.example.fitnesapp.data.model.entity.WeeklyProgressEntity
import com.example.fitnesapp.data.model.entity.WorkoutSessionEntity

@Database(
    entities = [ExerciseEntity::class, WorkoutSessionEntity::class, WeeklyProgressEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FitnessDatabase: RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
    abstract fun weeklyProgressDao(): WeeklyProgressDao

    companion object {
        const val DATABASE_NAME = "fitness_db"

        fun buildDatabase(context: Context): FitnessDatabase {
            return Room.databaseBuilder(
                context,
                FitnessDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private var INSTANCE: FitnessDatabase? = null

        fun getInstance(context: Context): FitnessDatabase {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE!!
        }
    }
}