package com.shamilov.core.components.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BannersComponentResponse(
    override val type: String?,
    val items: List<BannerComponentResponse>,
    val size: String,
) : ComponentResponse() {

    @Serializable
    class BannerComponentResponse(
        val image: String,
        @SerialName("deep_link")
        val deeplink: String,
    )
}