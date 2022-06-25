package com.shamilov.core.components.data.model

class BannersComponentResponse(
    type: ComponentType?,
    val items: List<BannerComponentResponse>
) : ComponentResponse(type)