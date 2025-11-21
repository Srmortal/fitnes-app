package com.example.fitnesapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesapp.screens.Dashboard
import com.example.fitnesapp.screens.ExerciseDetails
import java.net.URLDecoder

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination?.route
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home"
        ){
            composable("home") {
                Dashboard(
                    navController = navController
                )
            }
            composable(
                route = "workout/{exerciseId}/{exerciseName}/{imageUrl}/{difficulty}/{minutes}",
                arguments = listOf(
                    navArgument("exerciseId") { type = NavType.IntType },
                    navArgument("exerciseName") { type = NavType.StringType },
                    navArgument("imageUrl") { type = NavType.StringType },
                    navArgument("minutes") { type = NavType.IntType },
                    navArgument("difficulty") { type = NavType.StringType },
                )
            ) { backStackEntry ->
                val exerciseId = backStackEntry.arguments?.getInt("exerciseId")
                val exerciseName = backStackEntry.arguments?.getString("exerciseName")
                val minutes = backStackEntry.arguments?.getInt("minutes")
                val difficulty = backStackEntry.arguments?.getString("difficulty")
                val imageUrl = backStackEntry.arguments?.getString("imageUrl")?.let {
                    URLDecoder.decode(it, "UTF-8")
                }

                if (exerciseId != null && exerciseName != null && minutes != null && difficulty != null && imageUrl != null) {
                    ExerciseDetails(
                        exerciseName = exerciseName,
                        minutes = minutes,
                        difficulty = difficulty,
                        imageUrl = imageUrl,
                        exerciseId = exerciseId
                    )
                } else {
                    Text("Error: Missing required arguments.", modifier = modifier.padding(innerPadding))
                }
            }
        }
    }
}