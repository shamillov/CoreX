package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.BannersComponentResponse
import com.shamilov.core.components.domain.model.BannersComponent

class BannersComponentMapper(
    private val bannerComponentMapper: BannerComponentMapper,
) {
    fun mapBannersComponentResponse(response: BannersComponentResponse): BannersComponent {
        return BannersComponent(
            items = response.items.map { bannerComponentMapper.mapBannerComponent(it) }
        )
    }
}