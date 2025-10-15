package com.example.greenbin.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun OtpCodeField(
    codeLength: Int = 6,
    onCodeFilled: (String) -> Unit
) {
    var code by remember { mutableStateOf("") }

    BasicTextField(
        value = code,
        onValueChange = {
            if (it.length <= codeLength) {
                code = it
                if (it.length == codeLength) {
                    onCodeFilled(it)
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        decorationBox = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                repeat(codeLength) { index ->
                    val isFilled = index < code.length
                    val isActive = index == code.length

                    // 🔹 Анимация масштаба при вводе
                    val scale by animateFloatAsState(
                        targetValue = if (isFilled) 1.1f else 1f,
                        animationSpec = tween(durationMillis = 150),
                        label = "scale"
                    )

                    // 🔹 Анимация цвета
                    val bgColor by animateColorAsState(
                        targetValue = when {
                            isFilled -> Color(0xFFD1F7C4) // зелёный при вводе
                            isActive -> Color(0xFFE0E0E0) // серый для активной
                            else -> Color(0xFFF5F5F5)
                        },
                        animationSpec = tween(200),
                        label = "bgColor"
                    )

                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(52.dp)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            }
                            .clip(RoundedCornerShape(8.dp))
                            .background(bgColor)
                            .border(
                                width = 1.dp,
                                color = if (isActive) Color.Gray else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isFilled) code[index].toString() else "",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    )
}
