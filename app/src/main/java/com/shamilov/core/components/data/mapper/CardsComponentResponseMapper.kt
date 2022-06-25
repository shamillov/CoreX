package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.CardsComponentResponse
import com.shamilov.core.components.domain.model.CardComponent
import com.shamilov.core.components.domain.model.CardsComponent

class CardsComponentResponseMapper {
    fun mapCardsComponentResponse(response: CardsComponentResponse): CardsComponent {
        return CardsComponent(
            cards = response.items.map { mapCardComponentResponse(it) }
        )
    }

    private fun mapCardComponentResponse(response: CardsComponentResponse.CardComponentResponse): CardComponent {
        return CardComponent(
            image = response.image,
            deeplink = response.deeplink,
        )
    }
}