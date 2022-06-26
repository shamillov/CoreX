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
import com.shamilov.core.auth.data.local.AuthPreferences
import com.shamilov.core.auth.data.local.AuthPreferencesImpl
import com.shamilov.core.auth.data.remote.AuthNetworkApi
import com.shamilov.core.auth.data.remote.HttpClient
import com.shamilov.core.auth.data.repository.AuthRepositoryImpl
import com.shamilov.core.auth.domain.usecase.AuthUseCase
import com.shamilov.core.auth.domain.usecase.AuthUseCaseImpl
import com.shamilov.core.auth.presentation.auth.AuthScreen
import com.shamilov.core.presentation.main.MainScreen
import com.shamilov.core.auth.presentation.verification.CodeVerificationScreen
import com.shamilov.core.ui.theme.CoreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var authUseCase: AuthUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //temporary di
        load()

        setContent {
            val navController = rememberNavController()

            CoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
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
        val prefs: AuthPreferences = AuthPreferencesImpl(this)
        val httpClient = HttpClient.createHttpClient(prefs.getToken())
        val api = httpClient.create(AuthNetworkApi::class.java)
        val repository = AuthRepositoryImpl(api, prefs)
        val useCase: AuthUseCase = AuthUseCaseImpl(repository)
        authUseCase = useCase
//        val componentsApi = httpClient.create(ComponentsNetworkApi::class.java)
//        val bannerComponentMapper = BannerComponentMapper()
//        val componentsRepository: ComponentsRepository = ComponentsRepositoryImpl(componentsApi, ComponentsMapper(
//            HeaderComponentMapper(),
//            bannerComponentMapper,
//            BannersComponentMapper(bannerComponentMapper)
//        ) )
//        val componentsUseCase = ComponentsUseCase(componentsRepository)

        if (useCase.isAuthorize.not()) {
            lifecycleScope.launch {
                useCase.createUser()
                    .onFailure {
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
            }
        }
//        lifecycleScope.launch {
//            componentsUseCase.getComponents()
//                .onFailure { Log.d("qwer", it.message.toString()) }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoreTheme {
        MainScreen(rememberNavController())
    }
}