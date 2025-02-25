package com.meleha.animateddetails

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.meleha.animateddetails.ui.components.AnimatedDetailsApp
import com.meleha.animateddetails.ui.components.home.HomeViewModel
import com.meleha.animateddetails.ui.theme.AnimatedDetailsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            val homeViewModel: HomeViewModel by viewModels()
            AnimatedDetailsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimatedDetailsApp(
                        modifier = Modifier.padding(innerPadding),
                        homeViewModel
                    )
                }
            }
        }
    }
}