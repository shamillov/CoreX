package com.shamilov.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shamilov.core.ui.theme.Dimens

data class BannerViewData(
    val image: String,
    val bannerHeight: Dp
)

@Composable
fun BannerComponent(data: BannerViewData, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val image = data.image
    val bannerHeight = data.bannerHeight

    AsyncImage(
        model = image,
        contentDescription = null,
        placeholder = ColorPainter(color = MaterialTheme.colorScheme.secondary),
        error = ColorPainter(color = MaterialTheme.colorScheme.secondary),
        modifier = modifier
            .clip(RoundedCornerShape(Dimens.cornerRadius))
            .fillMaxWidth()
            .height(bannerHeight)
            .clickable { onClick() }
    )
}

@Composable
@Preview
fun BannerComponentPreview() {
    BannerComponent(data = BannerViewData(image = "", 100.dp)) {

    }
}