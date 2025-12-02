package com.example.greenbin.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenbin.R.color

@Composable
fun CustomButton(
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
                disabledContainerColor = colorResource(color.neutral3),
                disabledContentColor = colorResource(color.neutral8),
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
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
                disabledContainerColor = colorResource(color.neutral3),
                disabledContentColor = colorResource(color.neutral8),
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

@Composable
@Preview(showBackground = true)
fun previewButton(){
    CustomButton(
        text = "ПОсмотреть карту",
        modifier = Modifier.width(342.dp).height(44.dp),
        filled = true,
        enabled = true,
        onClick = {}
    )
}
