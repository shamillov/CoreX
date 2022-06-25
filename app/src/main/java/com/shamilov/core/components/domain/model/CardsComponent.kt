package com.shamilov.core.components.domain.model

data class CardsComponent(
    val cards: List<CardComponent>,
) : Component()

data class CardComponent(
    val image: String,
    val deeplink: String,
)