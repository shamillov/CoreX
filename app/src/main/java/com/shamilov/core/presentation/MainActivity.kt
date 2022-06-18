package com.shamilov.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shamilov.core.presentation.auth.AuthScreen
import com.shamilov.core.presentation.components.*
import com.shamilov.core.ui.theme.CoreTheme
import com.shamilov.core.utils.DefaultSpacer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ToolbarComponent { navController.navigate("auth") }

        BannerComponent(data = BannerViewData("", 100.dp)) { /* onClick() **/ }

        DefaultSpacer()
        HeaderComponent(data = HeaderViewData("Акции", "Скидка на заказ от 1000 ₽"))
        DefaultSpacer(8.dp)
        PromosComponent(
            data = PromosViewData(
                listOf(
                    PromoViewData("", ""),
                    PromoViewData("", ""),
                    PromoViewData("", ""),
                    PromoViewData("", ""),
                    PromoViewData("", ""),
                    PromoViewData("", ""),
                    PromoViewData("", ""),
                    PromoViewData("", ""),
                )
            )
        ) {

        }

        DefaultSpacer()
        HeaderComponent(data = HeaderViewData("Вы заказывали ранее", null))
        DefaultSpacer(8.dp)

        LazyRow {
            items(10) {
                ProductComponent(
                    data = ProductViewData(
                        "image",
                        "Пицца Пепперони",
                        "319 ₽",
                        "Из дади пицца"
                    ), modifier = Modifier.padding(horizontal = 8.dp)
                ) {

                }
            }
        }

        DefaultSpacer()
        HeaderComponent(data = HeaderViewData("Азиатская кухня", null))
        DefaultSpacer(8.dp)
        BannerComponent(data = BannerViewData("", 160.dp)) { /* onClick() **/ }
        DefaultSpacer()
        HeaderComponent(data = HeaderViewData("Горячие блюда", "Закажи от 2000 ₽ и получи по ебалу в подарок"))
        DefaultSpacer(8.dp)
        BannerComponent(data = BannerViewData("", 100.dp)) { /* onClick() **/ }
        DefaultSpacer()
        BannerComponent(data = BannerViewData("", 100.dp)) { /* onClick() **/ }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoreTheme {
        MainScreen(rememberNavController())
    }
}