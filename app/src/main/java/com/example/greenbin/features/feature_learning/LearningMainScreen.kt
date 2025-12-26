package com.example.greenbin.features.feature_learning

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.greenbin.navigation.AppScreen
import com.greenbin.ui.components.AppBottomNavigationBar

@Composable
fun LearningMainScreen(navController: NavHostController, viewModel: LearningViewModel = hiltViewModel()) {
    val topics by viewModel.topics.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {Text("Знай и действуй")},
        bottomBar = { AppBottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(topics) { topic ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(AppScreen.LearningTopic(topic.id)) },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E1)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Icon(
                                painterResource(topic.iconRes),
                                contentDescription = null,
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(topic.title, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    topic.subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                                Spacer(Modifier.height(8.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Пройдено тем")
                                    Spacer(Modifier.width(8.dp))
                                    LinearProgressIndicator(
                                        progress = topic.completedLessons.toFloat() / topic.totalLessons.coerceAtLeast(
                                            1
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text("${topic.completedLessons} из ${topic.totalLessons}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}