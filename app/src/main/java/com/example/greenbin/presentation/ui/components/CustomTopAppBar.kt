package com.example.greenbin.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    titleRes: Int,
    onBackClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    actionIcon: ImageVector? = null,
)  {
    TopAppBar(
        modifier = Modifier.padding(start = 16.dp),
        title = {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        // Стрелки задаются теперь так
                        // imageVector = actionIcon,
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Назад",
                    )
                }
            }
        },
        actions = {
            if (onActionClick != null && actionIcon != null){
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = "Действие"
                    )
                }
            }
        }
    )
}