package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.BannerComponentResponse
import com.shamilov.core.components.domain.model.BannerComponent

class BannerComponentMapper {
    fun mapBannerComponent(component: BannerComponentResponse): BannerComponent {
        return BannerComponent(
            component.image
        )
    }
}