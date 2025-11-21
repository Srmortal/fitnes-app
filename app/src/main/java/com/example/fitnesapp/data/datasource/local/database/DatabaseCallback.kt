package com.example.fitnesapp.data.datasource.local.database

import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitnesapp.SampleData
import com.example.fitnesapp.data.datasource.local.dao.ExerciseDao
import com.example.fitnesapp.data.datasource.local.dao.WeeklyProgressDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class DatabaseCallback(
    private val exerciseDaoProvider: Provider<ExerciseDao>,
    private val weeklyProgressDaoProvider: Provider<WeeklyProgressDao>
): RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    // This method is called by Room ONLY when the .db file is first created.
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) { // Use Dispatchers.IO for database operations
            populateDatabase()
        }
    }

    private suspend fun populateDatabase(){
        // Get the list of sample exercises
        val exercises = SampleData.sampleExercises
        val weeklyProgress = SampleData.sampleWeeklyProgress
        // --- ADD THE INSERTION LOGIC HERE ---
        // Use the DAO provider to insert the data.
        // `upsertExercise` is a good choice as it will insert or update.
        exercises.forEach {
            exerciseDaoProvider.get().upsertExercise(it)
            Log.d("DatabaseCallback", "Inserted exercise: $it")
        }
        weeklyProgressDaoProvider.get().upsertWeeklyProgress(weeklyProgress)
    }
}