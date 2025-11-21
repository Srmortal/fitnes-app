package com.example.fitnesapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.fitnesapp.Graph
import com.example.fitnesapp.InstructionsCard
import com.example.fitnesapp.R
import com.example.fitnesapp.WorkoutTimerCard
import com.example.fitnesapp.ui.theme.Blue600
import com.example.fitnesapp.ui.theme.Blue800
import com.example.fitnesapp.ui.theme.LightBlue
import com.example.fitnesapp.ui.theme.TransparentBlue
import com.example.fitnesapp.viewmodel.ExerciseDetailsViewModel
import com.example.fitnesapp.viewmodel.ExerciseDetailsViewModelFactory

@Composable
fun ExerciseDetails(
    exerciseId: Int,
    exerciseName: String?,
    minutes: Int?,
    difficulty: String?,
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    val viewModel: ExerciseDetailsViewModel = viewModel(
        factory = ExerciseDetailsViewModelFactory(
            exerciseRepository = Graph.exerciseRepository,
            weeklyProgressRepository = Graph.weeklyProgressRepository,
            exerciseId = exerciseId
        )
    )

    // Collect the isCompleted state from the ViewModel
    val isCompleted by viewModel.isCompleted.collectAsState()

    if (exerciseName == null || minutes == null || difficulty == null) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Error: Details not found.",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Please go back and try again.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        return
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            ExerciseDetailsTopBar(
                exerciseName = exerciseName,
                minutes = minutes,
                difficulty = difficulty,
                modifier = Modifier.fillMaxWidth()
            )
        },
    ) { innerPadding ->
        ExerciseDetailsContent(
            isCompleted = isCompleted,
            imageUrl = imageUrl,
            minutes = minutes,
            modifier = Modifier.padding(innerPadding),
            onMarkAsCompleted = {
                viewModel.markAsCompleted()
            }
        )
    }
}

@Composable
fun ExerciseDetailsTopBar(
    exerciseName: String,
    minutes: Int,
    difficulty: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Blue600, Blue800)
                )
            )
            .padding(8.dp)
    ) {
        Text(
            text = exerciseName,
            color = Color.White,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Box(
                modifier = Modifier
                    .background(
                        color = TransparentBlue,
                        shape = CircleShape
                    )
                    .padding(horizontal = 14.dp, vertical = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trending_up),
                        contentDescription = "Trending Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = difficulty,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = LightBlue,
                        shape = CircleShape
                    )
                    .padding(horizontal = 14.dp, vertical = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Clock Icon",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "$minutes minutes",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseDetailsContent(
    isCompleted: Boolean,
    minutes: Int,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    onMarkAsCompleted: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = imageUrl ?: "https://images.unsplash.com/photo-1544005313-94ddf0286df2",
            contentDescription = "Workout Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 15.dp, start = 16.dp, end = 16.dp)
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        WorkoutTimerCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            minutes = minutes
        )
        InstructionsCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Blue600,
                            Blue800,
                        )
                    ), shape = ButtonDefaults.shape
                )
                .height(ButtonDefaults.MinHeight),
            onClick = {
                onMarkAsCompleted()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color(0xFF4CAF50).copy(alpha = 0.5f).compositeOver(Color.Gray)
            ),
            enabled = !isCompleted
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Check Icon",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (!isCompleted) "Mark as Completed" else "Workout Completed",
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun ExerciseDetailsPreview() {
    ExerciseDetails(
        exerciseName = "Exercise Name",
        minutes = 10,
        difficulty = "Beginner",
        imageUrl = "https://images.unsplash.com/photo-1544005313-94ddf0286df2",
        exerciseId = 0
    )
}
