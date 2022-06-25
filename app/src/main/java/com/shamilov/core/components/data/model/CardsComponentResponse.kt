package com.shamilov.core.components.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CardsComponentResponse(
    override val type: String?,
    val items: List<CardComponentResponse>,
) : ComponentResponse() {

    @Serializable
    class CardComponentResponse(
        val image: String,
        @SerialName("deep_link")
        val deeplink: String,
    )
}

