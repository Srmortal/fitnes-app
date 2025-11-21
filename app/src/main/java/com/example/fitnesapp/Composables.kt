package com.example.fitnesapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.fitnesapp.ui.theme.Blue100
import com.example.fitnesapp.ui.theme.Blue200
import com.example.fitnesapp.ui.theme.Blue500
import com.example.fitnesapp.ui.theme.Blue600
import com.example.fitnesapp.ui.theme.Blue700
import com.example.fitnesapp.ui.theme.Blue800
import com.example.fitnesapp.ui.theme.LightBlue
import com.example.fitnesapp.ui.theme.Purple500
import kotlinx.coroutines.delay


@Composable
fun ProgressCard(
    total: Int,
    completed: Int
){
    val remaining = total - completed
    val percentage = (completed.toFloat() / total.toFloat())*100
    val progress = if (total > 0) {
        completed.toFloat() / total
    } else {
        0f
    }
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Blue500,
                            Blue700
                        )
                    )
                )
                .padding(20.dp)
        ){
            Column{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row{
                        Icon(
                            painter = painterResource(id = R.drawable.trophy),
                            contentDescription = "Trophy Icon",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Weekly Progress",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                color = LightBlue,
                                shape = CircleShape
                            )
                            .padding(horizontal = 14.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CheckCircle,
                                contentDescription = "Check Icon",
                                tint = Color.White
                            )
                            Text(
                                text = "$completed/$total",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(50)),
                    color = Color.Black,
                    trackColor = LightBlue,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$percentage% Completed", color = Color.White)
                    Text(text = "$remaining Remaining", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun QuickActionButton(
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit = {},
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor),
            onClick = onClick,
        ) {
            icon()
        }
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun WorkoutCard(
    workoutName: String,
    minutes: Int,
    imageUrl: String,
    foregroundColor: Color,
    backgroundColor: Color,
    difficulty: String = "Beginner",
    onStartClick: () -> Unit = {}
){
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(20.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Workout Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = workoutName, fontWeight = FontWeight.SemiBold)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.clock),
                                contentDescription = "Favorite Icon",
                            )
                            Text(text = "$minutes minutes", fontWeight = FontWeight.Medium)
                        }
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = backgroundColor)
                                .padding(vertical = 3.dp, horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.trending_up),
                                contentDescription = "trending",
                                tint = foregroundColor,
                            )
                            Text(
                                text = difficulty,
                                color = foregroundColor,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = {
                            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Blue200
                        )
                    ) {
                        Text(
                            text = "View Details",
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Blue600,
                                        Blue800,
                                    )
                                ), shape = ButtonDefaults.shape
                            )
                            .height(ButtonDefaults.MinHeight),
                        onClick = { onStartClick() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = "Start",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

enum class TimerState {
    Stopped,
    Running,
    Paused
}

@Composable
fun WorkoutTimerCard(
    minutes: Int,
    modifier: Modifier = Modifier
) {
    val totalTimeInMillis = remember { minutes * 60 * 1000L }
    var remainingTime by remember { mutableLongStateOf(totalTimeInMillis) }
    var timerState by remember { mutableStateOf(TimerState.Stopped) }

    // --- 2. Corrected Timer Logic ---
    LaunchedEffect(key1 = timerState) {
        // Only run the countdown when the state is 'Running'
        if (timerState == TimerState.Running) {
            while (remainingTime > 0) {
                delay(1000L) // Wait for 1 second
                remainingTime -= 1000L

                // If the state changes to Paused while in the loop, break out.
                if (timerState != TimerState.Running) {
                    break
                }
            }
        }
        // When the loop finishes because time ran out, update the state.
        if (remainingTime <= 0) {
            timerState = TimerState.Stopped
        }
    }

    // This is the time that counts up from 00:00
    val elapsedTime = totalTimeInMillis - remainingTime
    val elapsedSeconds = elapsedTime / 1000
    val displayElapsedMinutes = (elapsedSeconds / 60).toString().padStart(2, '0')
    val displayElapsedSeconds = (elapsedSeconds % 60).toString().padStart(2, '0')

    // 3. UI Calculations
    val progress = if (totalTimeInMillis > 0) {
        // Progress should count down
        elapsedTime.toFloat() / totalTimeInMillis
    } else {
        1f
    }

    // This is the time that counts down
    val remainingSeconds = remainingTime / 1000
    val displayRemainingMinutes = (remainingSeconds / 60).toString().padStart(2, '0')
    val displayRemainingSeconds = (remainingSeconds % 60).toString().padStart(2, '0')

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Purple500,
                            Blue600
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = "Favorite Icon",
                        )
                        Text(text = "Workout Timer", fontWeight = FontWeight.Medium)
                    }
                    Text(text = "$displayRemainingMinutes:$displayRemainingSeconds left", fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "$displayElapsedMinutes:$displayElapsedSeconds",
                        fontSize = 32.sp
                    )
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(50)),
                        color = Color.Black,
                        trackColor = LightBlue,
                        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            timerState = when (timerState) {
                                TimerState.Running -> TimerState.Paused // If running, pause it
                                else -> TimerState.Running // If stopped or paused, run it
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Blue600
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 3.dp,
                            pressedElevation = 3.dp,
                            hoveredElevation = 3.dp,
                            focusedElevation = 3.dp
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = if (timerState != TimerState.Running) painterResource(id = R.drawable.play_arrow) else painterResource(id = R.drawable.pause),
                                contentDescription = "Play Icon",
                            )
                            Text(text = if (timerState != TimerState.Running) "Start" else "Pause")
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            timerState = TimerState.Stopped
                            remainingTime = totalTimeInMillis
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Blue600,
                            containerColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Pause Icon",
                        )
                    }
                }
            }
        }
    }
}

data class Instruction(
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val isCurrent: Boolean = false
)

@Composable
fun InstructionsCard(
    modifier: Modifier = Modifier
) {
    var instructions by remember {
        mutableStateOf(
            listOf(
                Instruction(
                    title = "Warm-up",
                    description = "5 minutes of light cardio",
                    isCurrent = true // Start with the first item as current
                ),
                Instruction(
                    title = "Jumping Jacks",
                    description = "Perform for 30 seconds"
                ),
                Instruction(
                    title = "Cool-down",
                    description = "5 minutes of stretching"
                )
            )
        )
    }
    var progress by remember { mutableFloatStateOf(0f) }
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier =
            modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.checklist),
                            contentDescription = "Favorite Icon",
                            tint = Blue600
                        )
                        Text(
                            text = "Instructions",
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                    }
                    Text(
                        text = "${instructions.count { it.isCompleted }}/${instructions.size}",
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(50)),
                    color = Color.Black,
                    trackColor = Color.LightGray,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    instructions.forEachIndexed { index, instruction ->
                        InstructionStep(
                            order = index + 1,
                            instruction = instruction,
                            onToggle = {
                                // 3. This is the logic to update the state on click
                                instructions = instructions.mapIndexed { i, inst ->
                                    if (i == index) {
                                        // Toggle the completion status of the clicked item
                                        inst.copy(isCompleted = !inst.isCompleted)
                                    } else {
                                        inst
                                    }
                                }
                                // Update the progress based on the new state
                                progress = instructions.count { it.isCompleted }.toFloat() / instructions.size
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InstructionStep(
    order: Int,
    instruction: Instruction,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (instruction.isCompleted) Color(0xFFE3FCEF) else Color.Transparent
    val borderColor = if (instruction.isCompleted) Color(0xFF4CAF50) else Color.LightGray
    val iconColor = if (instruction.isCompleted) Color(0xFF4CAF50) else Color.Gray

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)) // Use clip for the background
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onToggle() } // 4. Make the entire Row clickable
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon section
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(
                    width = 1.5.dp,
                    color = iconColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (instruction.isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed Step",
                    tint = iconColor,
                    modifier = Modifier.size(16.dp)
                )
            }else{
                Text(
                    text = order.toString(),
                    fontWeight = FontWeight.Medium,
                    color = iconColor
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Text section
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = instruction.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = if (instruction.isCompleted) Color.Gray else Color.DarkGray,
                textDecoration = if(instruction.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )
            Text(
                text = instruction.description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
private fun InstructionsCardPreview() {
    InstructionsCard()
}

@Preview
@Composable
private fun WorkoutTimerCardPreview() {
    WorkoutTimerCard(
        minutes = 45
    )
}

@Preview
@Composable
fun ProgressCardPreview(){
    ProgressCard(total = 100, completed = 50)
}

@Preview
@Composable
fun QuickActionButtonPreview(){
    QuickActionButton(
        text = "Quick Action",
        backgroundColor = Blue100,
    ){
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite Icon",
            tint = Blue600
        )
    }
}

@Preview
@Composable
fun WorkoutCardPreview(){
    WorkoutCard(
        workoutName = "Workout Name",
        minutes = 30,
        imageUrl = "https://picsum.photos/",
        foregroundColor = Blue600,
        backgroundColor = Blue100,
    )
}