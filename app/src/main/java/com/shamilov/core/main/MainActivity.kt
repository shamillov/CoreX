package com.shamilov.core.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shamilov.core.auth.presentation.auth.AuthScreen
import com.shamilov.core.auth.presentation.verification.CodeVerificationScreen
import com.shamilov.core.common.di.daggerComponent
import com.shamilov.core.common.ui.theme.CoreTheme
import com.shamilov.core.components.presentation.MainScreen
import com.shamilov.core.profile.presentation.ProfileScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val viewModelFactory = daggerComponent.viewModelFactory

            CoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(
                                navController = navController,
                                viewModel = viewModel(factory = viewModelFactory),
                            )
                        }
                        composable("auth") {
                            AuthScreen(
                                navController = navController,
                                viewModel = viewModel(factory = viewModelFactory),
                            )
                        }
                        composable("verification") {
                            CodeVerificationScreen(
                                navController = navController,
                                viewModel = viewModel(factory = viewModelFactory),
                            )
                        }
                        composable("profile") {
                            ProfileScreen(
                                navController = navController,
                                viewModel = viewModel(factory = viewModelFactory),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoreTheme {
//        MainScreen(rememberNavController())
    }
}