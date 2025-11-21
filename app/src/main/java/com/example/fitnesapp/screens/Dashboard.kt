package com.example.fitnesapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesapp.Graph
import com.example.fitnesapp.ProgressCard
import com.example.fitnesapp.QuickActionButton
import com.example.fitnesapp.R
import com.example.fitnesapp.WorkoutCard
import com.example.fitnesapp.ui.theme.Blue100
import com.example.fitnesapp.ui.theme.Blue600
import com.example.fitnesapp.ui.theme.Blue800
import com.example.fitnesapp.ui.theme.FitnesAppTheme
import com.example.fitnesapp.ui.theme.Gray100
import com.example.fitnesapp.ui.theme.Gray600
import com.example.fitnesapp.ui.theme.Green100
import com.example.fitnesapp.ui.theme.Green600
import com.example.fitnesapp.ui.theme.Red100
import com.example.fitnesapp.ui.theme.Red600
import com.example.fitnesapp.viewmodel.DashboardViewModel
import com.example.fitnesapp.viewmodel.DashboardViewModelFactory
import java.net.URLEncoder

@Composable
fun Dashboard(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val dashboardViewModel = viewModel<DashboardViewModel>(
        factory = DashboardViewModelFactory(
            Graph.exerciseRepository,
            Graph.workoutSessionRepository,
            Graph.weeklyProgressRepository
        )
    )
    val exercises by dashboardViewModel.exercises.collectAsState()
    val workoutSessions by dashboardViewModel.workoutSessions.collectAsState()
    val weeklyProgress by dashboardViewModel.weeklyProgress.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Blue600, Blue800)
                        )
                    )
                    .padding(8.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.fire_ic),
                        contentDescription = "Fire Icon",
                        tint = Color.White
                    )
                    Text(
                        text = "Dashboard",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    text = "Keep going and achieve your goals!",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp), // Apply horizontal padding once here
            verticalArrangement = Arrangement.spacedBy(16.dp) // Adds space between all items
        ) {
            item {
                ProgressCard(
                    total = if(weeklyProgress.isEmpty()) 100 else weeklyProgress[0].total,
                    completed = if(weeklyProgress.isEmpty()) 50 else weeklyProgress[0].completed,
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ){
                    Text(
                        text = "Quick Access",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        QuickActionButton(
                            text = "My Workouts",
                            backgroundColor = Blue100,
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.dumbbell),
                                contentDescription = "Dumbbell Icon",
                                tint = Blue600
                            )
                        }
                        QuickActionButton(
                            text = "Favourites",
                            backgroundColor = Red100,
                        ){
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite Icon",
                                tint = Red600
                            )
                        }
                        QuickActionButton(
                            text = "Schedule",
                            backgroundColor = Green100,

                            ){
                            Icon(
                                imageVector = Icons.Outlined.DateRange,
                                contentDescription = "Date Icon",
                                tint = Green600
                            )
                        }
                        QuickActionButton(
                            text = "Settings",
                            backgroundColor = Gray100,
                        ){
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Settings Icon",
                                tint = Gray600
                            )
                        }
                    }
                }
            }
            item {
                Text(
                    text = "Available Workouts",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            if (exercises.isNotEmpty()){
                items(exercises) {
                    WorkoutCard(
                        workoutName = it.name,
                        minutes = it.duration,
                        imageUrl = it.imageUrl,
                        foregroundColor = if (it.isCompleted) Color.Gray else Blue600,
                        backgroundColor = if (it.isCompleted) Color.LightGray else Blue100,
                        onStartClick = {
                            val encodedUrl = URLEncoder.encode(it.imageUrl, "UTF-8")
                            navController.navigate("workout/${it.id}/${it.name}/$encodedUrl/${it.difficulty}/${it.duration}")
                        },
                        difficulty = it.difficulty
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            else{
                item {
                    val sampleImage = "https://plus.unsplash.com/premium_photo-1664474619075-644dd191935f?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    WorkoutCard(
                        workoutName = "No Available Workouts",
                        minutes = 25,
                        imageUrl = sampleImage,
                        foregroundColor = Gray600,
                        backgroundColor = Gray100,
                        onStartClick = {
                            val encodedUrl = URLEncoder.encode(sampleImage, "UTF-8")
                            navController.navigate("workout/0/name/$encodedUrl/Easy/25")
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun DashboardPreview() {
    FitnesAppTheme {
        Dashboard(
            navController = rememberNavController()
        )
    }
}