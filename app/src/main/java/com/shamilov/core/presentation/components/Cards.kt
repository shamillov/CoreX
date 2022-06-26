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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shamilov.core.ui.theme.Dimens

data class CardsViewData(
    val cards: List<CardViewData>,
)

data class CardViewData(
    val image: String,
    val deeplink: String,
)

@Composable
fun CardsComposable(data: CardsViewData, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val promos = data.cards

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(promos) { promo ->
            CardComposable(data = promo) {
                onClick()
            }
        }
    }
}

@Composable
fun CardComposable(data: CardViewData, modifier: Modifier = Modifier, onClick: () -> Unit) {
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
            contentScale = ContentScale.Crop,
            placeholder = ColorPainter(color = MaterialTheme.colorScheme.primary),
            error = ColorPainter(color = MaterialTheme.colorScheme.primary),
        )
    }
}