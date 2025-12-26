package com.example.greenbin.features.feature_learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningLessonScreen(
    topicId: String,
    lessonId: String,
    navController: NavHostController
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(
            title = { Text("Завершить") },
            navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, null) } }
        )

        Text("Что такое устойчивое развитие и зачем оно нужно", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        Text("Когда мы слышим слова вроде «экология», «экологичный», «устойчивое развитие» — кажется, что это что-то сложное, скучное и вообще не для нас. Но на самом деле — это про обычную жизнь. Про то, как мы едим, покупаем, ездим, отдыхаем и даже выбрасываем мусор.\n\nИ вот важный факт: наш образ жизни напрямую влияет на планету. То, как ты живёшь — влияет на леса в Сибири, ледники в Арктике и чистоту воздуха в твоём городе. Всё связано.\n\nПростой пример. Представь, что у тебя в холодильнике — еда на неделю. Ты можешь съесть всё за один день — вкусно, сильно, но завтра ничего не останется. Или можешь распределить продукты по дням, чтобы хватило надолго. То же самое с природой. У нас есть 'холодильник' с ресурсами: воздух, вода, леса, животные, нефть, газ. Мы можем 'съесть' всё быстро — и завтра ничего не останется. Или можем жить так, чтобы хватило на многие поколения.\n\nЭто и есть устойчивое развитие — жить сегодня, не разрушая завтра.")

        Spacer(Modifier.height(32.dp))

        Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Завершить")
        }
    }
}