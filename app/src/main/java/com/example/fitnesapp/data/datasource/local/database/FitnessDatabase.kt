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
import javax.inject.Provider

@Database(
    entities = [ExerciseEntity::class, WorkoutSessionEntity::class, WeeklyProgressEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FitnessDatabase: RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
    abstract fun weeklyProgressDao(): WeeklyProgressDao

    companion object {
        const val DATABASE_NAME = "fitness_db"
        @Volatile
        private var INSTANCE: FitnessDatabase? = null
        private fun buildDatabase(context: Context): FitnessDatabase {
            // 1. Declare a variable that will hold the database instance later.
            lateinit var database: FitnessDatabase

            // 2. Create a Provider. This is like a "promise" to provide the DAO.
            //    The code inside the braces is NOT run now. It only runs when .get() is called.
            val exerciseDaoProvider = Provider { database.exerciseDao() }
            val workoutSessionDaoProvider = Provider { database.workoutSessionDao() }
            val weeklyProgressDaoProvider = Provider { database.weeklyProgressDao() }

            // 3. Build the database instance and add the callback.
            database = Room.databaseBuilder(
                context.applicationContext,
                FitnessDatabase::class.java,
                DATABASE_NAME
            )
                // Pass the provider to the callback. This is safe.
                .addCallback(DatabaseCallback(
                    exerciseDaoProvider = exerciseDaoProvider,
                    weeklyProgressDaoProvider = weeklyProgressDaoProvider,
                ))
                // Use this to prevent crashes during development if you change the DB schema.
                .fallbackToDestructiveMigration(false)
                .build()

            // 4. Return the fully initialized database instance.
            return database
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        fun getInstance(context: Context): FitnessDatabase {
            return INSTANCE ?: synchronized(this) {
                buildDatabase(context).also { INSTANCE = it }
            }
        }
    }
}