package com.shamilov.core.components.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BannerComponentResponse(
    override val type: String?,
    val image: String,
    val size: String,
    @SerialName("deep_link")
    val deeplink: String,
) : ComponentResponse()