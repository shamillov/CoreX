package com.shamilov.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shamilov.core.ui.theme.Dimens

data class HeaderViewData(
    val title: String,
    val subtitle: String?,
)

@Composable
fun HeaderComposable(data: HeaderViewData, modifier: Modifier = Modifier) {
    val title = data.title
    val subtitle = data.subtitle

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = Dimens.largeText,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (!subtitle.isNullOrEmpty()) {
            Text(text = subtitle, fontSize = Dimens.smallText, modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
@Preview
fun HeaderComponentPreview() {
    Column {
        HeaderComposable(data = HeaderViewData("Promo", null))
        Spacer(modifier = Modifier.size(8.dp))
        HeaderComposable(data = HeaderViewData("Популярное", "Доступно на закакз от 1000"))
    }
}