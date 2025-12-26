package com.example.greenbin.features.feature_learning

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun LearningTestScreen(topicId: String, navController: NavHostController, viewModel: LearningViewModel = hiltViewModel()) {
    val tests by viewModel.getTests(topicId).collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // TopBar
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
            }
            Text("Введение в экологию и переработку", style = MaterialTheme.typography.titleLarge)
        }

        Spacer(Modifier.height(16.dp))

        CircularProgressIndicator(progress = 1f, modifier = Modifier.size(80.dp).align(Alignment.CenterHorizontally))

        Spacer(Modifier.height(16.dp))

        TabRow(selectedTabIndex = 0) {
            Tab(selected = true, onClick = { }) { Text("Категории") }
            Tab(selected = false, onClick = { }) { Text("История") }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(tests) { test ->
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E1))) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(test.title, style = MaterialTheme.typography.titleMedium)
                            Text("${test.questionsCount} вопросов")
                        }
                        if (test.isLocked) Icon(Icons.Default.Lock, contentDescription = "Заблокировано")
                        else if (test.isCompleted) Icon(Icons.Default.CheckCircle, contentDescription = "Пройдено", tint = Color.Green)

                        LinearProgressIndicator(progress = test.progressPercent / 100f, modifier = Modifier.width(80.dp))
                        Text("${test.progressPercent}%", modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }
    }
}