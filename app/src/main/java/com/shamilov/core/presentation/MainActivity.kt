package com.shamilov.core.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shamilov.core.App
import com.shamilov.core.auth.presentation.auth.AuthScreen
import com.shamilov.core.presentation.main.MainScreen
import com.shamilov.core.auth.presentation.verification.CodeVerificationScreen
import com.shamilov.core.common.di.DaggerComponent
import com.shamilov.core.ui.theme.CoreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        load()

        setContent {
            val navController = rememberNavController()

            CoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(navController) }
                        composable("auth") { AuthScreen(navController) }
                        composable("verification") { CodeVerificationScreen(navController) }
                    }
                }
            }
        }
    }

    private fun load() {
        val useCase = (application as DaggerComponent).appComponent.authUseCase()

        if (useCase.isAuthorize.not()) {
            lifecycleScope.launch {
                useCase.createUser()
                    .onFailure {
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoreTheme {
        MainScreen(rememberNavController())
    }
}