package com.example.greenbin.presentation.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenbin.R
import com.example.greenbin.presentation.ui.components.CustomTopAppBar
import com.example.greenbin.presentation.ui.components.CustomButton
import com.example.greenbin.presentation.ui.components.CustomInput
import org.w3c.dom.Text



//@Composable
//fun RegistrationScreen() {
//    Scaffold (
//        topBar = {
//            CustomTopAppBar(
//                titleRes =R.string.registration_appbar_text,
//                onBackClick = { TODO() },
//                onActionClick = { TODO() },
//            )
//        }
//    ){  innerPadding ->
//        Surface (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(top = 28.dp, start = 17.dp, end = 16.dp)
//        ){
//            RegistrationContent(modifier = Modifier.fillMaxSize())
//        }
//    }
//}
//
//@Composable
//fun RegistrationContent(modifier: Modifier = Modifier){
//    Column(
//        verticalArrangement = Arrangement.Top,
//        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        var checked by remember {mutableStateOf(true)}
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomInput(
//            modifier = Modifier,
//            textPlaceholder = "Имя",
//            isPasswordField = false,
//            value = "asdasdasd",
//            onValueChange = {},
//            textLabel = "Имя",
//            keyboardType = KeyboardType.Text,
//        )
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomInput(
//            modifier = Modifier,
//            textPlaceholder = "E-mail",
//            isPasswordField = false,
//            value = "asdasdasd",
//            onValueChange = {},
//            textLabel = "E-mail",
//            keyboardType = KeyboardType.Email,
//        )
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomInput(
//            modifier = Modifier,
//            textPlaceholder = "Пароль",
//            isPasswordField = true,
//            value = "dasdsadsa",
//            onValueChange = {},
//            textLabel = "Пароль",
//            keyboardType = KeyboardType.Password,
//        )
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomInput(
//            modifier = Modifier,
//            textPlaceholder = "Пароль",
//            isPasswordField = true,
//            value = "asdasdasd",
//            onValueChange = {},
//            textLabel = "Пароль",
//            keyboardType = KeyboardType.Password,
//        )
//        Spacer(
//            modifier = Modifier.height(4.dp)
//        )
//        Row (
//            modifier = Modifier.height(32.dp).fillMaxWidth().padding(start = 58.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ){
//            Checkbox(
//                checked = checked,
//                modifier = Modifier.size(18.dp),
//                colors = CheckboxDefaults.colors(
//                    checkedColor = colorResource(R.color.primary5), // зелёный при активном
//                    uncheckedColor = Color.Gray,      // серый при неактивном
//                    checkmarkColor = Color.White       // цвет галочки
//                ),
//                onCheckedChange = { checked = it }
//            )
//            Text(
//                modifier = modifier.padding(start = 4.dp).fillMaxWidth(),
//                text = stringResource(R.string.agree_license_agreement),
//                style = MaterialTheme.typography.bodySmall
//            )
//        }
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        CustomButton(
//            text = "Зарегистрироваться",
//            modifier = Modifier
//                .width(327.dp)
//                .height(52.dp),
//            filled = true,
//            enabled = false
//        ) { }
//        Spacer(
//            modifier = Modifier.height(16.dp)
//        )
//        TextButton(
//            onClick = {},
//            ) {
//            Text(
//                text = "Войти",
//                color = colorResource(R.color.primary5),
//                style = MaterialTheme.typography.titleSmall,
//            )
//        }
//    }
//}
//
//@Composable
//@Preview(
//    showSystemUi = true,
//    showBackground = true,)
//fun PreviewRegistrationContent(){
//    RegistrationScreen()
//}