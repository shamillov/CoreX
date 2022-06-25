package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.*
import com.shamilov.core.components.domain.model.Component

class ComponentsMapper(
    private val headerComponentMapper: HeaderComponentMapper,
    private val bannerComponentMapper: BannerComponentMapper,
    private val bannersComponentMapper: BannersComponentMapper,
    private val cardsComponentResponseMapper: CardsComponentResponseMapper,
) {
    fun mapComponents(response: List<ComponentResponse>): List<Component> {
        return response.mapNotNull { component ->
            when (component) {
                is HeaderComponentResponse -> headerComponentMapper.mapHeaderComponent(component)
                is BannerComponentResponse -> bannerComponentMapper.mapBannerComponent(component)
                is BannersComponentResponse -> bannersComponentMapper.mapBannersComponentResponse(component)
                is CardsComponentResponse -> cardsComponentResponseMapper.mapCardsComponentResponse(component)
                else -> null
            }
        }
    }
}