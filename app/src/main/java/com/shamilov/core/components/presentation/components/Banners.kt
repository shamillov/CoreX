package com.shamilov.core.components.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

data class BannersViewData(
    val banners: List<BannerViewData>
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannersComposable(data: BannersViewData) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val bannerWidth = screenWidth - 16.dp

    HorizontalPager(count = data.banners.size, contentPadding = PaddingValues(end = 16.dp)) { page ->
        BannerComposable(data = data.banners[page]) {

        }
    }
}