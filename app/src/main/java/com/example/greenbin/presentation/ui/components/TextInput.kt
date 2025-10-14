package com.example.greenbin.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CustomInput(modifier: Modifier=Modifier,
                textLabel: String,
                textPlaceholder: String,
                keyboardType: KeyboardType ){
    var value by remember { mutableStateOf("") }
    var newEmail: String = ""
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {newEmail -> value = textLabel},
        label = { Text(text = textLabel, style = MaterialTheme.typography.bodyMedium) },
        placeholder = { Text(text = textPlaceholder) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        )
    )
}


@Composable
@Preview(showBackground = true)
fun PreviewCustomInput(){
    CustomInput(
        modifier = Modifier.width(327.dp).height(52.dp),
        textLabel = "E-mail",
        textPlaceholder = "example@mail.com",
        keyboardType = KeyboardType.Email
    )
}
