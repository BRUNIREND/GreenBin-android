package com.example.greenbin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.greenbin.presentation.ui.components.CustomButton
import com.example.greenbin.presentation.navigation.AppNavGraph
import com.example.greenbin.presentation.ui.theme.GreenBinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenBinTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomButton(
        text = "Вход",
        Modifier.width(242.dp).height(44.dp),
        filled = true,
        enabled = false,
        onClick = { print("HEHEHEHHEH") },
    )
}