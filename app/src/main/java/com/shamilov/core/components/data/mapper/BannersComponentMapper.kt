package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.BannersComponentResponse
import com.shamilov.core.components.domain.model.BannerComponent
import com.shamilov.core.components.domain.model.BannersComponent
import javax.inject.Inject

class BannersComponentMapper @Inject constructor() {
    fun mapBannersComponentResponse(response: BannersComponentResponse): BannersComponent {
        return BannersComponent(
            items = response.items.map { mapBannerComponentResponse(it, response.size) },
        )
    }

    private fun mapBannerComponentResponse(
        response: BannersComponentResponse.BannerComponentResponse,
        size: String,
    ): BannerComponent {
        return BannerComponent(
            image = response.image,
            size = size,
            deeplink = response.deeplink,
        )
    }
}