package com.meleha.animateddetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.meleha.animateddetails.ui.components.detail.DetailsScreen
import com.meleha.animateddetails.ui.components.home.HomeScreen
import com.meleha.animateddetails.ui.components.home.HomeViewModel
import com.meleha.animateddetails.ui.theme.AnimatedDetailsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val exampleViewModel: HomeViewModel by viewModels()
            AnimatedDetailsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(exampleViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    AnimatedDetailsTheme {
        DetailsScreen()
    }
}