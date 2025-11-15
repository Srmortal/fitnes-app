package com.example.fitnesapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun ProgressCard(
    total: Int,
    completed: Int
){
    val remaining = total - completed
    val percentage = (completed.toFloat() / total.toFloat())*100
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
                    progress = 0.5f,
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
                        onClick = {},
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