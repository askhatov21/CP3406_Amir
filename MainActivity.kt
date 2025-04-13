package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.theme.MyApplicationTheme
import com.example.myapplication.ui.MainNavigation
import com.example.myapplication.viewmodel.TaskViewModel
import com.example.myapplication.ui.WelcomeScreen
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.ui.LoginScreen


enum class AppScreen {
    Welcome, Login, Main
}
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var screenState by remember { mutableStateOf(AppScreen.Welcome) }

            // ViewModel shared across screens
            val taskViewModel: TaskViewModel = viewModel ()

            MyApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    when (screenState) {
                        AppScreen.Welcome -> WelcomeScreen(
                            onGetStarted = { screenState = AppScreen.Login }
                        )

                        AppScreen.Login -> LoginScreen(
                            onLoginClicked = { screenState = AppScreen.Main },
                            onForgotPassword = { /* TODO */ },
                            onSignUp = { /* TODO */ }
                        )

                        AppScreen.Main -> MainNavigation(viewModel = taskViewModel)
                    }
                }
            }
        }
    }
}