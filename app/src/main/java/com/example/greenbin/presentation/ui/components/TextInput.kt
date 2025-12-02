package com.example.greenbin.presentation.ui.components

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class InputFieldType{
    USERNAME,
    PASSWORD,
    EMAIL
}
data class InputFieldConfig(
    val label: String,
    val keyboardType: KeyboardType,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val validator: (String) -> Boolean,
    val placeholder: String,
    val errorMessage: String
)

fun InputFieldType.getConfig(): InputFieldConfig {
    return when (this) {
        InputFieldType.EMAIL -> InputFieldConfig(
            label = "Email",
            keyboardType = KeyboardType.Email,
            visualTransformation = VisualTransformation.None,
            validator = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
            placeholder = "Email",
            errorMessage = "Некорректный email"
        )
        InputFieldType.PASSWORD -> InputFieldConfig(
            label = "Пароль",
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            validator = { it.length >= 6 },
            placeholder = "Password",
            errorMessage = "Минимум 6 символов"
        )
        InputFieldType.USERNAME -> InputFieldConfig(
            label = "Имя пользователя",
            keyboardType = KeyboardType.Text,
            visualTransformation = VisualTransformation.None,
            placeholder = "Username",
            validator = { it.length in 3..20 && it.all { c -> c.isLetterOrDigit() } },
            errorMessage = "Имя должно быть 3–20 символов, без пробелов"
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInput(
    value: String,
    type: InputFieldType,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
){
    val config = type.getConfig()
    var passwordVisible by remember { mutableStateOf(false) }

    // Проверка корректности
    val isValid = remember(value) { config.validator(value) }
    val isError = remember(value) { !isValid && value.isNotEmpty() }

    // Цвета рамки
    val borderColor = when {
        isError -> MaterialTheme.colorScheme.error
        isValid -> Color(0xFF4CAF50) // Зеленый при валидном вводе
        else -> MaterialTheme.colorScheme.outline
    }

    OutlinedTextField(
        modifier = Modifier.width(327.dp).height(62.dp),
        shape = RoundedCornerShape(8.dp),
        value = value,
        onValueChange = onValueChange,

        label = { Text(text = config.label) },
        placeholder = { if (config.placeholder.isNotEmpty()) Text(text = config.placeholder) },
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            cursorColor = borderColor
        ),
        visualTransformation = when (type) {
            InputFieldType.PASSWORD -> if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            else -> config.visualTransformation
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions(
            keyboardType = config.keyboardType,
            imeAction = ImeAction.Next
        ),

        trailingIcon = {
            if (type == InputFieldType.PASSWORD) {
                val image = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            } else if (isValid) {
                Icon(
                    // можно заменить на галочку ✅
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "Valid",
                    tint = Color(0xFF4CAF50)
                )
            }
        },

        )
    AnimatedVisibility(visible = isError) {
        Text(
            text = config.errorMessage,
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
//    Column (modifier = modifier.fillMaxHeight().height(120.dp)){
//
//
//    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_2")
fun PreviewCustomInput(){
    var email by remember { mutableStateOf("") }
    CustomInput(
        modifier = Modifier.width(327.dp).height(52.dp),
        onValueChange = { email = it },
        value = "asdasd",
        type = InputFieldType.USERNAME,
    )
}
