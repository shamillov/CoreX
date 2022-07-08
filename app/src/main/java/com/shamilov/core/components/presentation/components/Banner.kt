package com.shamilov.core.components.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shamilov.core.common.ui.theme.Dimens

data class BannerViewData(
    val image: String,
    val bannerSize: BannerSize,
    val deeplink: String,
)

enum class BannerSize(val height: Dp) {
    SMALL(100.dp), MEDIUM(200.dp), LARGE(300.dp),
}

@Composable
fun BannerComposable(
    data: BannerViewData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    val image = data.image
    val bannerSize = data.bannerSize

    AsyncImage(
        model = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = ColorPainter(color = MaterialTheme.colorScheme.primaryContainer),
        error = ColorPainter(color = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(Dimens.cornerRadius))
            .height(bannerSize.height)
            .clickable { onClick() },
    )
}

@Composable
@Preview
fun BannerComponentPreview() {
    BannerComposable(data = BannerViewData(image = "", BannerSize.SMALL, "")) {

    }
}