package com.shamilov.core.data.mapper

import com.shamilov.core.data.model.BannerComponentResponse
import com.shamilov.core.data.model.ComponentResponse
import com.shamilov.core.data.model.HeaderComponentResponse
import com.shamilov.core.domain.model.Component

class ComponentsMapper(
    private val headerComponentMapper: HeaderComponentMapper,
    private val bannerComponentMapper: BannerComponentMapper,
) {
    fun mapComponents(components: List<ComponentResponse>): List<Component> {
        return components.map { component ->
            when (component) {
                is HeaderComponentResponse -> headerComponentMapper.mapHeaderComponent(component)
                is BannerComponentResponse -> bannerComponentMapper.mapBannerComponent(component)
                else -> error("The component $component is not available")
            }
        }
    }
}