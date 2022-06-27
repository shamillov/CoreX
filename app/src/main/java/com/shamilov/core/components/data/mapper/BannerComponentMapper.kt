package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.BannerComponentResponse
import com.shamilov.core.components.domain.model.BannerComponent
import javax.inject.Inject

class BannerComponentMapper @Inject constructor() {
    fun mapBannerComponent(response: BannerComponentResponse): BannerComponent {
        return BannerComponent(
            image = response.image,
            size = response.size,
            deeplink = response.deeplink,
        )
    }
}