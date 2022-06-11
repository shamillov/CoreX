package com.shamilov.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shamilov.core.ui.theme.Dimens

data class PromosViewData(
    val promos: List<PromoViewData>,
)

data class PromoViewData(
    val id: String,
    val image: String,
)

@Composable
fun PromosComponent(data: PromosViewData, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val promos = data.promos

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(promos) { promo ->
            PromoComponent(data = promo) {
                onClick()
            }
        }
    }
}

@Composable
fun PromoComponent(data: PromoViewData, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val image = data.image

    Box(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(Dimens.cornerRadius))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            placeholder = ColorPainter(color = MaterialTheme.colorScheme.primary),
            error = ColorPainter(color = MaterialTheme.colorScheme.primary),
        )
    }
}