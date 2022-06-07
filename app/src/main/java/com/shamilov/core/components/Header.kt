package com.shamilov.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class HeaderViewData(
    val title: String,
    val subtitle: String?,
)

@Composable
fun HeaderComponent(data: HeaderViewData, modifier: Modifier = Modifier) {
    val title = data.title
    val subtitle = data.subtitle

    Column(modifier = modifier) {
        Text(text = title, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)

        if (!subtitle.isNullOrEmpty()) {
            Text(text = subtitle, fontSize = 22.sp)
        }
    }
}

@Composable
@Preview
fun HeaderComponentPreview() {
    Column {
        HeaderComponent(data = HeaderViewData("Promo", null))
        Spacer(modifier = Modifier.size(8.dp))
        HeaderComponent(data = HeaderViewData("Популярное", "Доступно на закакз от 1000"))
    }
}