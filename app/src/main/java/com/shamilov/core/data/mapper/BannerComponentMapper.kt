package com.shamilov.core.data.mapper

import com.shamilov.core.data.model.BannerComponentResponse
import com.shamilov.core.domain.model.BannerComponent

class BannerComponentMapper {
    fun mapBannerComponent(component: BannerComponentResponse): BannerComponent {
        return BannerComponent(
            component.image
        )
    }
}