package com.example.greenbin.features.feature_learning

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(testId: String, navController: NavHostController, viewModel: LearningViewModel = hiltViewModel()) {
    val questions by viewModel.getQuestions(testId).collectAsStateWithLifecycle()

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Spacer(Modifier.height(16.dp))
                Text("Загрузка вопросов...")
            }
        }
        return
    }

    if (showResult) {
        AlertDialog(
            onDismissRequest = { navController.popBackStack() },
            title = { Text("Тест завершён") },
            text = { Text("Верных ответов: $score из ${questions.size}\nПроцент: ${(score * 100 / questions.size)}%") },
            confirmButton = {
                TextButton(onClick = { navController.popBackStack() }) {
                    Text("OK")
                }
            }
        )
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Вопрос ${currentQuestionIndex + 1} из ${questions.size}") },
                    navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, null) } }
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text(questions[currentQuestionIndex].text, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(16.dp))

                questions[currentQuestionIndex].options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { selectedOption = index }
                    ) {
                        RadioButton(selected = selectedOption == index, onClick = { selectedOption = index })
                        Text(option, modifier = Modifier.padding(start = 8.dp))
                    }
                }

                Spacer(Modifier.height(32.dp))

                Button(
                    onClick = {
                        if (selectedOption == questions[currentQuestionIndex].correctOptionIndex) score++
                        selectedOption = null
                        if (currentQuestionIndex < questions.size - 1) {
                            currentQuestionIndex++
                        } else {
                            showResult = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = selectedOption != null
                ) {
                    Text(if (currentQuestionIndex < questions.size - 1) "Далее" else "Завершить")
                }
            }
        }
    }
}