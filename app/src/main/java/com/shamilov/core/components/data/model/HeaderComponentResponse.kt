package com.shamilov.core.components.data.model

class HeaderComponentResponse(
    type: ComponentType,
    val title: String,
    val subtitle: String?,
) : ComponentResponse(type)