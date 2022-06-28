package com.shamilov.core.components.presentation.mapper

import com.shamilov.core.components.domain.model.*
import com.shamilov.core.components.presentation.components.*
import javax.inject.Inject

private const val BANNER_SMALL = "small"
private const val BANNER_MEDIUM = "medium"
private const val BANNER_LARGE = "large"

class ComponentsViewDataMapper @Inject constructor() {

    fun mapComponentsView(components: List<Component>): List<Any> {
        val list = mutableListOf<Any>()

        components.forEach { component ->
            when (component) {
                is HeaderComponent -> list.add(mapHeaderComponent(component))
                is BannerComponent -> list.add(mapBannerComponent(component))
                is BannersComponent -> list.add(mapBannersComponent(component))
                is CardsComponent -> list.add(mapCardsComponent(component))
            }
        }

        return list
    }

    private fun mapHeaderComponent(component: HeaderComponent): HeaderViewData {
        return HeaderViewData(
            title = component.title,
            subtitle = component.subtitle,
        )
    }

    private fun mapBannerComponent(component: BannerComponent): BannerViewData {
        return BannerViewData(
            image = component.image,
            bannerSize = mapBannerSize(component.size),
            deeplink = component.deeplink,
        )
    }

    private fun mapBannersComponent(component: BannersComponent): BannersViewData {
        return BannersViewData(
            banners = component.items.map(::mapBannerComponent)
        )
    }

    private fun mapBannerSize(size: String): BannerViewData.BannerSize {
        return when (size) {
            BANNER_SMALL -> BannerViewData.BannerSize.SMALL
            BANNER_MEDIUM -> BannerViewData.BannerSize.MEDIUM
            BANNER_LARGE -> BannerViewData.BannerSize.LARGE
            else -> error("The $size size not supported")
        }
    }

    private fun mapCardsComponent(component: CardsComponent): CardsViewData {
        return CardsViewData(
            cards = component.cards.map(::mapCardComponent)
        )
    }

    private fun mapCardComponent(component: CardComponent): CardViewData {
        return CardViewData(
            image = component.image,
            deeplink = component.deeplink,
        )
    }
}