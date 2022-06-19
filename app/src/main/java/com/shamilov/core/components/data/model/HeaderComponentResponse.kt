package com.shamilov.core.components.data.model

class HeaderComponentResponse(
    type: String,
    val title: String,
    val subtitle: String?,
) : ComponentResponse(type)