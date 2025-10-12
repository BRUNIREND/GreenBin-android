package com.example.greenbin.presentation.ui.screens.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenbin.R
import com.example.greenbin.presentation.ui.components.MainButton

data class ColorPalette(
    val mainColor: Color,
    val singleTheme: Color,
    val oppositeTheme: Color,
    val buttonColor: Color,
)




@Composable
fun OnBoardingPage(){
    val image = painterResource(R.drawable.trashholderbig)
    Column(
        modifier = Modifier.fillMaxSize().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.width(89.dp).height(97.dp)
        )

        Text(
            text = "GreenBin",
            fontSize = 64.sp,
            letterSpacing = -5.sp,
            fontFamily = FontFamily(Font(R.font.lato_medium))

        )
        Text(
            text = "get best",
            fontSize = 36.sp,
            letterSpacing = 4.sp,
            fontFamily = FontFamily(Font(R.font.lato_medium))
        )
        Spacer(modifier = Modifier.height(104.dp))
        MainButton(
            text = "Создать аккаунт",
            modifier = Modifier.width(242.dp).height(44.dp),
            filled = true,
            enabled = true,
            onClick = {},
        )
        Spacer(modifier = Modifier.height(16.dp))
        MainButton(
            text = "Вход",
            modifier = Modifier.width(242.dp).height(44.dp),
            filled = true,
            enabled = true,
            onClick = {},
        )
        Spacer(modifier = Modifier.height(16.dp))
        MainButton(
            text = "Посмотреть карту",
            modifier = Modifier.width(242.dp).height(44.dp),
            filled = false,
            enabled = true,
            onClick = {},
        )

    }
}

@Composable
@Preview(showBackground = true)
fun ShowOnBoardPreview(){
    OnBoardingPage()
}
