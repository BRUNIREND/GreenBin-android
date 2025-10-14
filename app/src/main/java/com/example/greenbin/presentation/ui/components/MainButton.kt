package com.example.greenbin.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenbin.R
import com.example.greenbin.R.color

@Composable
fun MainButton(
    text: String,
    modifier: Modifier = Modifier,
    filled: Boolean = true,
    enabled: Boolean = true,
    onClick: () -> Unit
){
    if (filled) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(color.primary4),
                contentColor = Color.White,
                disabledContainerColor = colorResource(R.color.neutral3),
                disabledContentColor = colorResource(R.color.neutral8),
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }else{
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = colorResource(color.primary4),
                disabledContainerColor = colorResource(R.color.neutral3),
                disabledContentColor = colorResource(R.color.neutral8),
            ),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(
                color = colorResource(color.primary4),
                width = 1.dp,
            )
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}