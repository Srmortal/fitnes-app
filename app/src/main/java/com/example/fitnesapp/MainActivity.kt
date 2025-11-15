package com.example.fitnesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.fitnesapp.data.datasource.local.database.FitnessDatabase
import com.example.fitnesapp.screens.Dashboard
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Graph.provide(this)
        setContent {
            FitnesAppTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Dashboard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}