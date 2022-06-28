package com.shamilov.core.components.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shamilov.core.common.ui.theme.Dimens

data class ProductViewData(
    val image: String,
    val name: String,
    val price: String,
    val description: String?,
)

@Composable
fun ProductComposable(data: ProductViewData, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val image = data.image
    val name = data.name
    val price = data.price
    val description = data.description

    Column(modifier = modifier
        .width(120.dp)
        .clip(RoundedCornerShape(Dimens.cornerRadius))
        .background(color = MaterialTheme.colorScheme.secondaryContainer)
        .clickable {  }
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            placeholder = ColorPainter(color = MaterialTheme.colorScheme.primary),
            error = ColorPainter(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = price,
            fontSize = Dimens.smallText,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = name,
            fontSize = Dimens.smallText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onClick() },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Добавить")
        }
    }
}

@Composable
@Preview
fun ProductComponentPreview() {
    val data = ProductViewData(
        "image",
        "Пицца Пепперони",
        "319 р",
        "Из дади пицца"
    )

    ProductComposable(data = data) {

    }
}