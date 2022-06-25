package com.shamilov.core.components.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BannersComponentResponse(
    override val type: String?,
    val items: List<BannerComponentResponse>,
) : ComponentResponse() {

    @Serializable
    class BannerComponentResponse(
        val image: String,
        val size: String,
        @SerialName("deep_link")
        val deeplink: String,
    )
}