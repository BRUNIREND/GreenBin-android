package com.example.greenbin.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.map.model.RecyclingPoint

@Composable
fun PointDetailContent(point: RecyclingPoint, onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(point.name, style = MaterialTheme.typography.headlineSmall)
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Закрыть")
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Адрес: ${point.address}")
        Text("Телефон: ${point.phone ?: "Не указан"}")
        Text("Часы работы: ${point.workingHours}")
        Text("Принимают: ${point.acceptedMaterials}")

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { /* открыть Yandex Navigator */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Проложить маршрут")
        }

        Spacer(Modifier.height(32.dp))
    }
}