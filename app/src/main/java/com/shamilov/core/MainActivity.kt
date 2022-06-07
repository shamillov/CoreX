package com.shamilov.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shamilov.core.components.*
import com.shamilov.core.ui.theme.CoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column {
        ToolbarComponent(modifier = Modifier.fillMaxWidth()) {
            //onClick
        }

        BannerComponent(data = BannerViewData("", 100.dp), modifier = Modifier.padding(horizontal = 8.dp)) {
            //onClick
        }
        Spacer(modifier = Modifier.size(16.dp))
        HeaderComponent(data = HeaderViewData("Акции", "Скидка на заказ от 1000"))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoreTheme {
        MainScreen()
    }
}