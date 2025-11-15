package com.example.fitnesapp.screens

import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesapp.Graph
import com.example.fitnesapp.data.model.entity.ExerciseEntity
import com.example.fitnesapp.data.model.entity.WorkoutSessionEntity
import com.example.fitnesapp.data.repository.ExerciseRepository
import com.example.fitnesapp.viewmodel.DashboardViewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
@Composable
fun Dashboard(
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
                    text = "Dashboard1",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ProgressCard(
                total = if(weeklyProgress.isEmpty()) 0 else weeklyProgress[0].total,
                completed = if(weeklyProgress.isEmpty()) 0 else weeklyProgress[0].completed,
            )
            Spacer(modifier = Modifier.height(16.dp))
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
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    text = "Available Workouts",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(exercises){
                        WorkoutCard(
                            workoutName = it.name,
                            minutes = it.duration,
                            imageUrl = it.imageUrl,
                            foregroundColor = Blue600,
                            backgroundColor = Blue100,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    FitnesAppTheme {
        Dashboard()
    }
}