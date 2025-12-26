package com.example.greenbin.features.feature_learning

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.greenbin.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningTopicScreen(topicId: String, navController: NavHostController, viewModel: LearningViewModel = hiltViewModel()) {
    val lessons by viewModel.getLessons(topicId).collectAsStateWithLifecycle()
    val tests by viewModel.getTests(topicId).collectAsStateWithLifecycle()
    var selectedTab by remember { mutableStateOf(0) } // 0 = Теория, 1 = Тесты

    Column(modifier = Modifier.fillMaxSize()) {
        // TopBar
        TopAppBar(
            title = { Text("Введение в экологию и переработку") },
            navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, null) } }
        )

        // Прогресс
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            CircularProgressIndicator(progress = 1f, modifier = Modifier.size(60.dp))
            Spacer(Modifier.width(16.dp))
            Text("100%", style = MaterialTheme.typography.titleLarge)
        }

        // Табы
        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) { Text("Теория") }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) { Text("Тесты") }
        }

        if (selectedTab == 0) {
            LazyColumn(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(lessons) { lesson ->
                    Card(
                        modifier = Modifier.clickable { navController.navigate(AppScreen.LearningLesson(topicId, lesson.id)) },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E1))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(lesson.title, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                LinearProgressIndicator(progress = if (lesson.isCompleted) 1f else 0f, modifier = Modifier.weight(1f))
                                Spacer(Modifier.width(8.dp))
                                Text("${lesson.pages} страниц")
                            }
                        }
                    }
                }
            }
        } else {
            LazyColumn(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(tests) { test ->
                    Card(
                        modifier = Modifier.clickable { if (!test.isLocked) navController.navigate(AppScreen.LearningTest(test.id)) },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E1))
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(test.title, style = MaterialTheme.typography.titleMedium)
                                Text("${test.questionsCount} вопросов")
                            }
                            if (test.isLocked) Icon(Icons.Default.Lock, null)
                            LinearProgressIndicator(progress = test.progressPercent / 100f, modifier = Modifier.width(80.dp))
                            Text("${test.progressPercent}%", modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
        }
    }
}