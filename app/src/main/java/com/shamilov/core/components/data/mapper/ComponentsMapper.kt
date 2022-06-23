package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.BannerComponentResponse
import com.shamilov.core.components.data.model.ComponentResponse
import com.shamilov.core.components.data.model.HeaderComponentResponse
import com.shamilov.core.components.domain.model.Component

class ComponentsMapper(
    private val headerComponentMapper: HeaderComponentMapper,
    private val bannerComponentMapper: BannerComponentMapper,
) {
    fun mapComponents(components: List<ComponentResponse>): List<Component> {
        return components.mapNotNull { component ->
            when (component) {
                is HeaderComponentResponse -> headerComponentMapper.mapHeaderComponent(component)
                is BannerComponentResponse -> bannerComponentMapper.mapBannerComponent(component)
                else -> null
            }
        }
    }
}