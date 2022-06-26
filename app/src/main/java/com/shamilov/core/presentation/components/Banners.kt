package com.shamilov.core.presentation.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

data class BannersViewData(
    val banners: List<BannerViewData>
)

@Composable
fun BannersComposable(data: BannersViewData) {
    LazyRow {
        items(data.banners) {
            BannerComposable(data = it) {

            }
        }
    }
}