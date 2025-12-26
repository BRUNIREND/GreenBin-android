package com.example.greenbin.features.feature_map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.greenbin.features.feature_map.viewmodel.PointDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PointDetailScreen(
    pointId: String,
    navController: NavHostController,
    viewModel: PointDetailViewModel = hiltViewModel()
) {
    val point by viewModel.point.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(point?.name ?: "Пункт приёма") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (point == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Категория (чип)
                FilterChip(
                    selected = true,
                    onClick = { },
                    label = { Text(point!!.categoryName) },
                    leadingIcon = {
                        Icon(painterResource(point!!.iconRes), contentDescription = null)
                    }
                )

                // Адрес
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFF00BFA5))
                    Spacer(Modifier.width(8.dp))
                    Text(point!!.address, style = MaterialTheme.typography.bodyLarge)
                }

                // Телефон (если есть)
                if (!point!!.phone.isNullOrBlank()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = Color(0xFF00BFA5))
                        Spacer(Modifier.width(8.dp))
                        Text(point!!.phone, style = MaterialTheme.typography.bodyLarge)
                    }
                }

                // Часы работы
                Text("Часы работы:", style = MaterialTheme.typography.titleMedium)
                Text(point!!.workingHours, style = MaterialTheme.typography.bodyMedium)

                // Что принимают
                Text("Принимают:", style = MaterialTheme.typography.titleMedium)
                Text(point!!.acceptedMaterials, style = MaterialTheme.typography.bodyMedium)

                // Кнопка "Проложить маршрут"
                Button(
                    onClick = { /* открыть Yandex Navigator или Google Maps */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFA5))
                ) {
                    Text("Проложить маршрут", color = Color.White)
                }
            }
        }
    }
}