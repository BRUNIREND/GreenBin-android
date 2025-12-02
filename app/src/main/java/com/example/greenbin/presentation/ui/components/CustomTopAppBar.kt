package com.example.greenbin.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class) // Исправляй дебил
@Composable
fun CustomTopAppBar(
    titleRes: Int, //Айдишник ресурса текста
    onBackClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    actionIcon: ImageVector? = null,
)  {
    TopAppBar(
//        modifier = Modifier.padding(start = 16.dp).fillMaxWidth(),
        title = {
            Row (modifier = Modifier.fillMaxWidth()){
                Text(
                    text = stringResource(titleRes),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
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
@Composable
@Preview(showBackground = true, device = "id:pixel_2")
fun previewTopBar(){
    Scaffold (
        topBar = {
            CustomTopAppBar(
                titleRes = com.example.greenbin.R.string.registrate_text,
            )
        }
    ){ innerPadding ->
        Text(text = "Hello")

    }
}