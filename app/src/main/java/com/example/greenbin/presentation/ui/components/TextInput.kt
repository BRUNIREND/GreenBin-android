package com.example.greenbin.presentation.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CustomInput(
    modifier: Modifier = Modifier,
    textPlaceholder: String = "",
    isPasswordField: Boolean = false,
    icon: ImageVector? = null,
    onIconClick: (() -> Unit)? = null,
    value: String,
    onValueChange: (String) -> Unit,
    textLabel: String,
    keyboardType: KeyboardType,
){
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = textLabel, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium) },
        placeholder = { if (textPlaceholder.isNotEmpty()) Text(text = textPlaceholder, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium) },
        singleLine = true,
        visualTransformation = when {
            isPasswordField && !passwordVisible -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            when {
                isPasswordField -> {
                    val visibilityIcon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    val description = if (passwordVisible) "Скрыть пароль" else "Показать пароль"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = visibilityIcon, contentDescription = description)
                    }
                }
                icon != null && onIconClick != null -> {
                    IconButton(onClick = onIconClick) {
                        Icon(imageVector = icon, contentDescription = "Иконка")
                    }
                }
            }
        },
    )
}


@Composable
@Preview(showBackground = true)
fun PreviewCustomInput(){
    var email by remember { mutableStateOf("") }
    CustomInput(
        modifier = Modifier.width(327.dp).height(52.dp),
        textLabel = "E-mail",
        textPlaceholder = "example@mail.com",
        keyboardType = KeyboardType.Email,
        onValueChange = { email = it },
        value = "asdsadsa"
    )
}
