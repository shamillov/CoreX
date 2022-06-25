package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.BannersComponentResponse
import com.shamilov.core.components.domain.model.BannerComponent
import com.shamilov.core.components.domain.model.BannersComponent

class BannersComponentMapper {
    fun mapBannersComponentResponse(response: BannersComponentResponse): BannersComponent {
        return BannersComponent(
            items = response.items.map { mapBannerComponentResponse(it) },
        )
    }

    private fun mapBannerComponentResponse(
        response: BannersComponentResponse.BannerComponentResponse,
    ): BannerComponent {
        return BannerComponent(
            image = response.image,
            size = response.size,
            deeplink = response.deeplink,
        )
    }
}